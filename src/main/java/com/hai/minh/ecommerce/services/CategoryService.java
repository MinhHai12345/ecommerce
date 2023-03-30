package com.hai.minh.ecommerce.services;

import com.hai.minh.ecommerce.dtos.products.CSVProductDTO;
import com.hai.minh.ecommerce.entities.CategoryEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.Map;

public interface CategoryService {

    boolean importCategories(MultipartFile file);

    Map<String, CategoryEntity> getCategoryMaps(Collection<CSVProductDTO> productDTOS);

    CategoryEntity create(String name);

    CategoryEntity getCategoryEntity(Map<String, CategoryEntity> categoryMaps, String name);

}