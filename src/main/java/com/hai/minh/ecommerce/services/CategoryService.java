package com.hai.minh.ecommerce.services;

import com.hai.minh.ecommerce.dtos.products.CSVProductDTO;
import com.hai.minh.ecommerce.entities.CategoryEntity;

import java.util.List;
import java.util.Map;

public interface CategoryService {
    void saveCategoryWithCSV( List<CSVProductDTO> csvProductDTOs);

    boolean existCategory(CSVProductDTO csvProductDTO, Map<String, CategoryEntity> categoryEntityMap);
}