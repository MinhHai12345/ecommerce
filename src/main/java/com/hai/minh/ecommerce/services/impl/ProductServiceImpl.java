package com.hai.minh.ecommerce.services.impl;

import com.hai.minh.ecommerce.dtos.products.CSVProductDTO;
import com.hai.minh.ecommerce.entities.CategoryEntity;
import com.hai.minh.ecommerce.entities.ProductEntity;
import com.hai.minh.ecommerce.entities.SubCategoryEntity;
import com.hai.minh.ecommerce.ep.converter.EPProductConverter;
import com.hai.minh.ecommerce.ep.dtos.products.EPProductDto;
import com.hai.minh.ecommerce.repository.ProductRepository;
import com.hai.minh.ecommerce.services.CategoryService;
import com.hai.minh.ecommerce.services.ProductService;
import com.hai.minh.ecommerce.services.SubCategoryService;
import com.hai.minh.ecommerce.utils.CSVUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SubCategoryService subCategoryService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private EPProductConverter epProductConverter;

    @Override
    @Transactional
    public boolean importProducts(MultipartFile file) {
        try {
            List<CSVProductDTO> productDTOS = CSVUtils.csvToDTO(file, CSVProductDTO.class);
            if (CollectionUtils.isNotEmpty(productDTOS)) {
                Map<String, ProductEntity> productMaps = this.getProductMaps(productDTOS);
                Map<String, CategoryEntity> categoryEntityMap = categoryService.getCategoryMaps(productDTOS);
                Map<String, SubCategoryEntity> subCategoryEntityMap = subCategoryService.getSubCategoryMaps(productDTOS);

                List<ProductEntity> productEntities = productDTOS.stream()
                        .map(it -> {
                            ProductEntity product = productMaps.get(it.getModel());
                            if (product == null) {
                                product = new ProductEntity();
                            }
                            product.setName(it.getName());
                            product.setSku(it.getModel());
                            product.setImageUrl(it.getImageUrl());
                            product.setPrice(it.getCurrentPrice());
                            product.setCategory(categoryService.getCategoryEntity(categoryEntityMap, it.getCategory()));
                            product.setSubCategory(subCategoryService.getSubCategoryEntity(categoryEntityMap,
                                    subCategoryEntityMap, it.getCategory(), it.getSubCategory()));
                            return product;
                        }).collect(Collectors.toList());
                if (CollectionUtils.isNotEmpty(productEntities)) {
                    productRepository.saveAll(productEntities);
                }
                return true;
            }
        } catch (Exception e) {
            logger.error(" Import product fail::: ".concat(e.getMessage()));
        }
        return false;
    }

    private Map<String, ProductEntity> getProductMaps(List<CSVProductDTO> productDTOs) {
        if (org.apache.commons.collections.CollectionUtils.isNotEmpty(productDTOs)) {
            return Collections.emptyMap();
        }
        Set<String> skus = productDTOs.stream()
                .map(CSVProductDTO::getModel)
                .collect(Collectors.toSet());
        List<ProductEntity> products = productRepository.findBySkuIn(skus);

        return products.stream()
                .collect(Collectors.toMap(ProductEntity::getSku, Function.identity()));
    }

    @Override
    public ResponseEntity<Map<String, Object>> createProductToEP() {
        logger.info("START PRODUCT SERVICES");
        List<ProductEntity> products = productRepository.findAll();
        if (CollectionUtils.isNotEmpty(products)) {
            for (ProductEntity product : products) {
                logger.info("LOOP FOREACH PRODUCT SERVICES");
                final EPProductDto request = epProductConverter.convertToEpProductData(product);
//                amqpTemplate.convertAndSend(rabbitConfigProperties.getQueueCreateProduct(), request);
            }
        }
        logger.info("END PRODUCT SERVICES");
        return null;
    }
}