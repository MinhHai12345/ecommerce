package com.hai.minh.ecommerce.services.impl;

import com.hai.minh.ecommerce.dtos.products.CSVProductDTO;
import com.hai.minh.ecommerce.entities.CategoryEntity;
import com.hai.minh.ecommerce.entities.SubCategoryEntity;
import com.hai.minh.ecommerce.repository.SubCategoryRepository;
import com.hai.minh.ecommerce.services.CategoryService;
import com.hai.minh.ecommerce.services.SubCategoryService;
import com.hai.minh.ecommerce.utils.CSVUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class SubCategoryServiceImpl implements SubCategoryService {
    private static final Logger logger = LoggerFactory.getLogger(SubCategoryServiceImpl.class);

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Autowired
    private CategoryService categoryService;

    @Override
    @Transactional
    public boolean importSubCategories(MultipartFile file) {
        try {
            List<CSVProductDTO> productDTOS = CSVUtils.csvToDTO(file, CSVProductDTO.class);
            if (CollectionUtils.isNotEmpty(productDTOS)) {
                Map<String, CategoryEntity> categoryMaps = categoryService.getCategoryMaps(productDTOS);
                Map<String, SubCategoryEntity> subCategoryMaps = this.getSubCategoryMaps(productDTOS);

                List<SubCategoryEntity> subCategoryEntities = productDTOS.stream()
                        .map(it -> {
                            SubCategoryEntity entity = subCategoryMaps.get(it.getSubCategory());
                            if (entity == null) {
                                entity = new SubCategoryEntity();
                                entity.setName(it.getSubCategory());
                            }
                            entity.setCategory(categoryService.getCategoryEntity(categoryMaps, it.getCategory()));
                            return entity;
                        }).collect(Collectors.toList());
                if (CollectionUtils.isNotEmpty(subCategoryEntities)) {
                    subCategoryRepository.saveAll(subCategoryEntities);
                }
                return true;
            }
        } catch (Exception e) {
            logger.error(" Import sub categories fail::: ".concat(e.getMessage()));
        }
        return false;
    }

    @Override
    public Map<String, SubCategoryEntity> getSubCategoryMaps(Collection<CSVProductDTO> productDTOS) {
        if (CollectionUtils.isEmpty(productDTOS)) {
            return Collections.emptyMap();
        }
        Set<String> categoryNames = productDTOS.stream()
                .map(CSVProductDTO::getSubCategory)
                .collect(Collectors.toSet());
        return subCategoryRepository.findByNameIn(categoryNames)
                .stream().collect(Collectors.toMap(SubCategoryEntity::getName, Function.identity()));
    }


    @Override
    public SubCategoryEntity getSubCategoryEntity(Map<String, CategoryEntity> categoryMaps,
            Map<String, SubCategoryEntity> subCategoryMaps, String categoryName, String subCategoryName) {
        SubCategoryEntity entity = subCategoryMaps.get(subCategoryName);
        if (entity == null) {
            entity = new SubCategoryEntity();
            entity.setName(subCategoryName);
        }
        entity.setCategory(categoryService.getCategoryEntity(categoryMaps, categoryName));
        return entity;
    }
}