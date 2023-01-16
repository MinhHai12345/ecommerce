package com.hai.minh.ecommerce.services.impl;

import com.hai.minh.ecommerce.dtos.products.CSVProductDTO;
import com.hai.minh.ecommerce.services.BrandService;
import com.hai.minh.ecommerce.services.CategoryService;
import com.hai.minh.ecommerce.services.ProductService;
import com.hai.minh.ecommerce.services.SubCategoryService;
import com.hai.minh.ecommerce.utils.CSVUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    private final CategoryService categoryService;
    private final BrandService brandService;


    @Autowired
    public ProductServiceImpl(CategoryService categoryService, BrandService brandService) {
        this.categoryService = categoryService;
        this.brandService = brandService;
    }


    @Override
    public void importProducts(MultipartFile file) {
        List<CSVProductDTO> productDTOS = CSVUtils.csvToDTO(file, CSVProductDTO.class);
        if (CollectionUtils.isNotEmpty(productDTOS)) {

            /** SAVE CATEGORIES AND SUBCATEGORIES*/
            categoryService.saveCategoryWithCSV(productDTOS);

            /** SAVE BRAND*/
            brandService.saveBrandWithCSV(productDTOS);

        }
//            productDTOS.stream()
//                    .forEach(product -> {
//                        ProductEntity productEntity = productRepository.findByName(product.getName());
//                        if (productEntity == null) {
//                            productEntity = new ProductEntity();
//                            productEntity.setCurrency(product.getCurrency());
//                            productEntity.setName(product.getName());
//                            productEntity.setImageUrl(product.getImageUrl());
//                            productRepository.save(productEntity);
//                        }
//                        productDTOS.stream();
//                    });

    }
}