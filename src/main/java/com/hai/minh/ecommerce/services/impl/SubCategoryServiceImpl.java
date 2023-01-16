package com.hai.minh.ecommerce.services.impl;

import com.hai.minh.ecommerce.dtos.products.CSVProductDTO;
import com.hai.minh.ecommerce.entities.CategoryEntity;
import com.hai.minh.ecommerce.entities.SubCategoryEntity;
import com.hai.minh.ecommerce.repository.CategoryRepository;
import com.hai.minh.ecommerce.repository.SubCategoryRepository;
import com.hai.minh.ecommerce.services.CategoryService;
import com.hai.minh.ecommerce.services.SubCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SubCategoryServiceImpl implements SubCategoryService {
    private final CategoryRepository categoryRepository;

    private final SubCategoryRepository subCategoryRepository;

    @Autowired
    public SubCategoryServiceImpl(SubCategoryRepository subCategoryRepository,
                                  CategoryRepository categoryRepository) {
        this.subCategoryRepository = subCategoryRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<SubCategoryEntity> saveSubCategoryWithCSV(List<CSVProductDTO> csvProductDTOs, CategoryEntity categoryEntity) {

        Set<String> subCategoryNames = csvProductDTOs.stream().map(CSVProductDTO::getSubCategory)
                .collect(Collectors.toSet());

        List<SubCategoryEntity> subCategoryServices = subCategoryRepository.findByNameIn(subCategoryNames);

        Map<String, SubCategoryEntity> subCategoryEntityMap = subCategoryServices.stream()
                .collect(Collectors.toMap(SubCategoryEntity::getName, Function.identity()));
        List<SubCategoryEntity> subCategories = csvProductDTOs.stream()
                .filter(filter -> existSubCategory(filter, subCategoryEntityMap)
                ).map(it -> {
                    SubCategoryEntity subCategoryEntity = new SubCategoryEntity();
                    subCategoryEntity.setName(it.getSubCategory());
                    subCategoryEntity.setCategory(categoryEntity);
                    return subCategoryEntity;
                }).collect(Collectors.toList());
        return subCategories;
    }

    @Override
    public boolean existSubCategory(CSVProductDTO csvProductDTO, Map<String, SubCategoryEntity> subCategoryEntityMap) {
        boolean isNotExist = subCategoryEntityMap.get(csvProductDTO.getSubCategory()) == null;
        if(isNotExist){
            subCategoryEntityMap.put(csvProductDTO.getSubCategory(), new SubCategoryEntity());
        }
        return isNotExist;
    }
}