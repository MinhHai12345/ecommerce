package com.hai.minh.ecommerce.services.impl;

import com.hai.minh.ecommerce.dtos.products.CSVProductDTO;
import com.hai.minh.ecommerce.entities.CategoryEntity;
import com.hai.minh.ecommerce.repository.CategoryRepository;
import com.hai.minh.ecommerce.services.CategoryService;
import com.hai.minh.ecommerce.utils.CSVUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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
public class CategoryServiceImpl implements CategoryService {
    private static final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    @Transactional
    public boolean importCategories(MultipartFile file) {
        try {
            List<CSVProductDTO> productDTOS = CSVUtils.csvToDTO(file, CSVProductDTO.class);
            if (CollectionUtils.isNotEmpty(productDTOS)) {
                Map<String, CategoryEntity> categoryEntityMaps = this.getCategoryMaps(productDTOS);

                List<CategoryEntity> categoryEntities = productDTOS.stream()
                        .filter(it -> categoryEntityMaps.get(it.getCategory()) == null)
                        .map(category -> {
                            CategoryEntity entity = new CategoryEntity();
                            entity.setName(category.getCategory());
                            return entity;
                        }).collect(Collectors.toList());
                if (CollectionUtils.isNotEmpty(categoryEntities)) {
                    categoryRepository.saveAll(categoryEntities);
                }
                return true;
            }
        } catch (Exception e) {
            logger.error(" Import categories fail::: ".concat(e.getMessage()));
        }
        return false;
    }

    @Override
    public Map<String, CategoryEntity> getCategoryMaps(Collection<CSVProductDTO> productDTOS) {
        if (CollectionUtils.isEmpty(productDTOS)) {
            return Collections.emptyMap();
        }
        Set<String> categoryNames = productDTOS.stream()
                .map(CSVProductDTO::getCategory)
                .collect(Collectors.toSet());
        return categoryRepository.findByNameIn(categoryNames)
                .stream().collect(Collectors.toMap(CategoryEntity::getName, Function.identity()));
    }

    @Override
    @Transactional
    public CategoryEntity create(String name) {
        if (StringUtils.isNotEmpty(name)) {
            CategoryEntity categoryEntity = new CategoryEntity();
            categoryEntity.setName(name);
            return categoryRepository.save(categoryEntity);
        }
        return null;
    }

    @Override
    @Transactional
    public CategoryEntity getCategoryEntity(Map<String, CategoryEntity> categoryMaps, String name) {
        CategoryEntity categoryEntity = categoryMaps.get(name);
        return categoryEntity != null ? categoryEntity : this.create(name);
    }
}