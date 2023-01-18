package com.hai.minh.ecommerce.services.impl;

import com.hai.minh.ecommerce.dtos.products.CSVProductDTO;
import com.hai.minh.ecommerce.entities.BrandEntity;
import com.hai.minh.ecommerce.entities.CategoryEntity;
import com.hai.minh.ecommerce.entities.ProductEntity;
import com.hai.minh.ecommerce.entities.SubCategoryEntity;
import com.hai.minh.ecommerce.repository.BrandRepository;
import com.hai.minh.ecommerce.repository.CategoryRepository;
import com.hai.minh.ecommerce.repository.ProductRepository;
import com.hai.minh.ecommerce.repository.SubCategoryRepository;
import com.hai.minh.ecommerce.services.BrandService;
import com.hai.minh.ecommerce.services.CategoryService;
import com.hai.minh.ecommerce.services.ProductService;
import com.hai.minh.ecommerce.services.SubCategoryService;
import com.hai.minh.ecommerce.utils.CSVUtils;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    private final CategoryService categoryService;
    private final BrandService brandService;
    private final SubCategoryService subCategoryService;

    private final ProductRepository productRepository;
    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;
    private final SubCategoryRepository subCategoryRepository;


    @Autowired
    public ProductServiceImpl(CategoryService categoryService, BrandService brandService, ProductRepository productRepository, BrandRepository brandRepository, CategoryRepository categoryRepository, SubCategoryService subCategoryService, SubCategoryRepository subCategoryRepository) {
        this.categoryService = categoryService;
        this.brandService = brandService;
        this.productRepository = productRepository;
        this.brandRepository = brandRepository;
        this.categoryRepository = categoryRepository;
        this.subCategoryService = subCategoryService;
        this.subCategoryRepository = subCategoryRepository;
    }


    @Override
    public void importProducts(MultipartFile file) {
        List<CSVProductDTO> productDTOS = CSVUtils.csvToDTO(file, CSVProductDTO.class);
        if (CollectionUtils.isNotEmpty(productDTOS)) {

            List<CategoryEntity> categories = categoryService.saveCategoryWithCSV(productDTOS);
            List<SubCategoryEntity> subCategories = subCategoryService.saveSubCategoryWithCSV(productDTOS, categories);
            List<BrandEntity> brands = brandService.saveBrandWithCSV(productDTOS);

            saveproductWithCSV(productDTOS, categories, subCategories, brands);
        }
    }

    @Override
    public void saveproductWithCSV(List<CSVProductDTO> csvProductDTOs, List<CategoryEntity> categories,
                                   List<SubCategoryEntity> subCategories, List<BrandEntity> brands) {

        List<ProductEntity> products = productRepository.findAll();

        Map<String, ProductEntity> productEntityMap = products.stream()
                .collect(Collectors.toMap(ProductEntity::getName, Function.identity()));

        Map<String, CategoryEntity> categoryEntityMap = categories.stream()
                .collect(Collectors.toMap(CategoryEntity::getName, Function.identity()));

        Map<String, SubCategoryEntity> subCategoryEntityMap = subCategories.stream()
                .collect(Collectors.toMap(SubCategoryEntity::getName, Function.identity()));

        Map<String, BrandEntity> brandEntityMap = brands.stream()
                .collect(Collectors.toMap(BrandEntity::getName, Function.identity()));


        List<ProductEntity> productEntities = csvProductDTOs.stream()
                .filter(filter -> existProduct(filter, productEntityMap, categoryEntityMap, subCategoryEntityMap, brandEntityMap)
                ).map(it -> {
                    ProductEntity product = new ProductEntity();

                    product.setName(productEntityMap.get(it.getName()).getName());
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
    public boolean existProduct(CSVProductDTO csvProductDTO, Map<String, ProductEntity> productEntityMap, Map<String, CategoryEntity> categoryEntityMap,
                                Map<String, SubCategoryEntity> subCategoryEntityMap, Map<String, BrandEntity> brandEntityMap) {
        boolean isNotExist = productEntityMap.get(csvProductDTO.getName()) == null;
        if (isNotExist) {
            productEntityMap.put(csvProductDTO.getName(), new ProductEntity());
        }
        return isNotExist;
    }
}