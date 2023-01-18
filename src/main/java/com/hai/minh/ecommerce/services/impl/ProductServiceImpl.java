package com.hai.minh.ecommerce.services.impl;

import com.hai.minh.ecommerce.dtos.products.CSVProductDTO;
import com.hai.minh.ecommerce.entities.BrandEntity;
import com.hai.minh.ecommerce.entities.CategoryEntity;
import com.hai.minh.ecommerce.entities.ProductEntity;
import com.hai.minh.ecommerce.entities.SubCategoryEntity;
import com.hai.minh.ecommerce.repository.CategoryRepository;
import com.hai.minh.ecommerce.repository.ProductRepository;
import com.hai.minh.ecommerce.services.BrandService;
import com.hai.minh.ecommerce.services.CategoryService;
import com.hai.minh.ecommerce.services.ProductService;
import com.hai.minh.ecommerce.services.SubCategoryService;
import com.hai.minh.ecommerce.utils.CSVUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {
    private final CategoryService categoryService;
    private final BrandService brandService;
    private final SubCategoryService subCategoryService;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ProductServiceImpl(CategoryService categoryService, BrandService brandService, SubCategoryService subCategoryService, ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.categoryService = categoryService;
        this.brandService = brandService;
        this.subCategoryService = subCategoryService;
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    @Override
    public boolean importProducts(MultipartFile file) {
        List<CSVProductDTO> productDTOS = CSVUtils.csvToDTO(file, CSVProductDTO.class);
        if (CollectionUtils.isNotEmpty(productDTOS)) {

            List<CategoryEntity> categories = categoryService.saveCategoryWithCSV(productDTOS);

            List<CategoryEntity> findAll = categoryRepository.findAll();
            List<CategoryEntity> newCategoriesEntity = new ArrayList<>(Stream.of(findAll, categories)
                    .flatMap(List::stream)
                    .collect(Collectors.toMap(CategoryEntity::getName, d -> d,
                            (CategoryEntity x, CategoryEntity y) -> x)).values());

            List<SubCategoryEntity> subCategories = subCategoryService.saveSubCategoryWithCSV(productDTOS, newCategoriesEntity);
            List<BrandEntity> brands = brandService.saveBrandWithCSV(productDTOS);
            saveproductWithCSV(productDTOS, newCategoriesEntity, subCategories, brands);
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public void saveproductWithCSV(List<CSVProductDTO> csvProductDTOs, List<CategoryEntity> categories,
                                   List<SubCategoryEntity> subCategories, List<BrandEntity> brands) {

        Set<String> skuNames = csvProductDTOs.stream()
                .map(CSVProductDTO::getModel)
                .collect(Collectors.toSet());
        List<ProductEntity> products = productRepository.findBySkuIn(skuNames);

        Map<String, ProductEntity> productEntityMap = products.stream()
                .collect(Collectors.toMap(ProductEntity::getSku, Function.identity()));

        Map<String, CategoryEntity> categoryEntityMap = categories.stream()
                .collect(Collectors.toMap(CategoryEntity::getName, Function.identity()));

        Map<String, SubCategoryEntity> subCategoryEntityMap = subCategories.stream()
                .collect(Collectors.toMap(SubCategoryEntity::getName, Function.identity()));

        Map<String, BrandEntity> brandEntityMap = brands.stream()
                .collect(Collectors.toMap(BrandEntity::getName, Function.identity()));

        List<ProductEntity> productEntities = csvProductDTOs.stream()
                .filter(filter -> existProduct(filter, productEntityMap)
                ).map(it -> {
                    ProductEntity product = new ProductEntity();

                    product.setName(it.getName());
                    product.setSku(it.getModel());
                    product.setImageUrl(it.getImageUrl());

                    product.setBrand(brandEntityMap.get(it.getBrand()));
                    product.setSubCategory(subCategoryEntityMap.get(it.getSubCategory()));
                    product.setCategory(categoryEntityMap.get(it.getCategory()));

                    return product;
                }).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(productEntities)) {
            productRepository.saveAll(productEntities);
        }
    }

    @Override
    public boolean existProduct(CSVProductDTO csvProductDTO, Map<String, ProductEntity> productEntityMap) {
        boolean isNotExist = productEntityMap.get(csvProductDTO.getModel()) == null;
        if (isNotExist) {
            productEntityMap.put(csvProductDTO.getModel(), new ProductEntity());
        }
        return isNotExist;
    }
}