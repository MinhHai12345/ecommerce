package com.hai.minh.ecommerce.services.impl;

import com.hai.minh.ecommerce.dtos.products.CSVProductDTO;
import com.hai.minh.ecommerce.entities.CategoryEntity;
import com.hai.minh.ecommerce.repository.CategoryRepository;
import com.hai.minh.ecommerce.services.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    @Override
    public List<CategoryEntity> saveCategoryWithCSV(List<CSVProductDTO> csvProductDTOs) {

        Set<String> categoryNames = csvProductDTOs.stream()
                .map(CSVProductDTO::getCategory)
                .collect(Collectors.toSet());
        List<CategoryEntity> categoryEntities = categoryRepository.findByNameIn(categoryNames);

        Map<String, CategoryEntity> categoryEntityMap = categoryEntities.stream().collect(Collectors.toMap(CategoryEntity::getName, Function.identity()));

        List<CategoryEntity> categories = csvProductDTOs.stream().filter(filter -> existCategory(filter, categoryEntityMap)).map(it -> {
            CategoryEntity categoryEntity = new CategoryEntity();
            categoryEntity.setName(it.getCategory());
            return categoryEntity;
        }).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(categories)) {
            return categoryRepository.saveAll(categories);
        }
        return Collections.emptyList();
    }

    @Override
    public boolean existCategory(CSVProductDTO csvProductDTO, Map<String, CategoryEntity> categoryEntityMap) {
        boolean isNotExist = categoryEntityMap.get(csvProductDTO.getCategory()) == null;
        if (isNotExist) {
            categoryEntityMap.put(csvProductDTO.getCategory(), new CategoryEntity());
        }
        return isNotExist;
    }
}