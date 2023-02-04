package com.hai.minh.ecommerce.services.impl;

import com.hai.minh.ecommerce.dtos.products.CSVProductDTO;
import com.hai.minh.ecommerce.entities.CategoryEntity;
import com.hai.minh.ecommerce.entities.SubCategoryEntity;
import com.hai.minh.ecommerce.repository.SubCategoryRepository;
import com.hai.minh.ecommerce.services.SubCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
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
public class SubCategoryServiceImpl implements SubCategoryService {
    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Transactional
    @Override
    public List<SubCategoryEntity> saveSubCategoryWithCSV(List<CSVProductDTO> csvProductDTOs, List<CategoryEntity> categoryEntities) {

        Map<String, CategoryEntity> categoryEntityMap = categoryEntities.stream()
                .collect(Collectors.toMap(CategoryEntity::getName, Function.identity()));

        Set<String> subCategoryNames = csvProductDTOs.stream()
                .map(CSVProductDTO::getSubCategory)
                .collect(Collectors.toSet());

        List<SubCategoryEntity> subCategoryServices = subCategoryRepository.findByNameIn(subCategoryNames);

        Map<String, SubCategoryEntity> subCategoryEntityMap = subCategoryServices.stream()
                .collect(Collectors.toMap(SubCategoryEntity::getName, Function.identity()));

        List<SubCategoryEntity> subCategories = csvProductDTOs.stream()
                .filter(e -> existSubCategory(e, subCategoryEntityMap, categoryEntityMap.get(e.getCategory())))
                .map(it -> {
                    SubCategoryEntity subCategoryEntity = new SubCategoryEntity();
                    subCategoryEntity.setName(it.getSubCategory());
                    subCategoryEntity.setCategory(categoryEntityMap.get(it.getCategory()));
                    return subCategoryEntity;
                }).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(subCategories)) {
            return subCategoryRepository.saveAll(subCategories);
        }
        return Collections.emptyList();
    }

    @Override
    public boolean existSubCategory(CSVProductDTO csvProductDTO, Map<String, SubCategoryEntity> subCategoryEntityMap, CategoryEntity category) {
        boolean isNotExist = subCategoryEntityMap.get(csvProductDTO.getSubCategory()) == null && category != null
                && category.getName().equals(csvProductDTO.getCategory());
        if (isNotExist) {
            subCategoryEntityMap.put(csvProductDTO.getSubCategory(), new SubCategoryEntity());
        }
        return isNotExist;
    }
}