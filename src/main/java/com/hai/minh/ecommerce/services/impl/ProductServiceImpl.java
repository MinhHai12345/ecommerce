package com.hai.minh.ecommerce.services.impl;

import com.hai.minh.ecommerce.dtos.products.CSVProductDTO;
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
    private final SubCategoryService subCategoryService;

    @Autowired
    public ProductServiceImpl(CategoryService categoryService, SubCategoryService subCategoryService) {
        this.categoryService = categoryService;
        this.subCategoryService = subCategoryService;
    }


    @Override
    public void importProducts(MultipartFile file) {
        List<CSVProductDTO> productDTOS = CSVUtils.csvToDTO(file, CSVProductDTO.class);
        if (CollectionUtils.isNotEmpty(productDTOS)) {

            /** SAVE CATEGORIES */
            categoryService.saveCategoryWithCSV(productDTOS);

//            /** SAVE SUBCATEGORY */
//            subCategoryService.saveSubCategoryWithCSV(productDTOS);



        }

//            productDTOS.stream()
//                    .collect(Collectors.groupingBy(CSVProductDTO::getBrand))
//                    .entrySet()
//                    .stream()
//                    .filter(entry -> entry.getValue().size() > 1)
//                    .forEach(brand -> {
//                        BrandEntity brandEntity = brandRepository.findByName(brand.getKey());
//                        if (brandEntity == null) {
//                            brandEntity = new BrandEntity();
//                            brandEntity.setName(brand.getKey());
//                            BrandEntity finalBrandEntity = brandEntity;
//                            brand.getValue().forEach(brandValue -> {
//                                if (brand.getKey().equals(brandValue.getBrand())) {
//                                    finalBrandEntity.setBrandUrl(brandValue.getBrandUrl());
//                                }
//                            });
//                            brandRepository.save(brandEntity);
//                        }
//                    });
//
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