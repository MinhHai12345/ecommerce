package com.hai.minh.ecommerce.services;

import com.hai.minh.ecommerce.dtos.products.CSVProductDTO;
import com.hai.minh.ecommerce.entities.CategoryEntity;
import com.hai.minh.ecommerce.entities.SubCategoryEntity;

import java.util.List;
import java.util.Map;

public interface SubCategoryService {

    List<SubCategoryEntity> saveSubCategoryWithCSV(List<CSVProductDTO> csvProductDTOs, List<CategoryEntity> categoryEntities);

    boolean existSubCategory(CSVProductDTO csvProductDTO, Map<String, SubCategoryEntity> subCategoryEntityMap, CategoryEntity category);
}