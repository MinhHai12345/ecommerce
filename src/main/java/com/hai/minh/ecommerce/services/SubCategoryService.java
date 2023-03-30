package com.hai.minh.ecommerce.services;

import com.hai.minh.ecommerce.dtos.products.CSVProductDTO;
import com.hai.minh.ecommerce.entities.CategoryEntity;
import com.hai.minh.ecommerce.entities.SubCategoryEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.Map;

public interface SubCategoryService {

    boolean importSubCategories(MultipartFile file);

    Map<String, SubCategoryEntity> getSubCategoryMaps(Collection<CSVProductDTO> productDTOS);

    SubCategoryEntity getSubCategoryEntity(Map<String, CategoryEntity> categoryMaps,
       Map<String, SubCategoryEntity> subCategoryMaps, String categoryName, String subCategoryName);
}