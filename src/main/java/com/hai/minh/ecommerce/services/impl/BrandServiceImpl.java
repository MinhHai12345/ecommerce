package com.hai.minh.ecommerce.services.impl;

import com.hai.minh.ecommerce.dtos.products.CSVProductDTO;
import com.hai.minh.ecommerce.entities.BrandEntity;
import com.hai.minh.ecommerce.entities.CategoryEntity;
import com.hai.minh.ecommerce.entities.SubCategoryEntity;
import com.hai.minh.ecommerce.repository.BrandRepository;
import com.hai.minh.ecommerce.services.BrandService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;

    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public void saveBrandWithCSV(List<CSVProductDTO> csvProductDTOs) {

        Set<String> brandName = csvProductDTOs.stream()
                .map(CSVProductDTO::getBrand)
                .collect(Collectors.toSet());

        List<BrandEntity> brandEntities = brandRepository.findByNameIn(brandName);

        Map<String, BrandEntity> brandEntityMap = brandEntities.stream()
                .collect(Collectors.toMap(BrandEntity::getName, Function.identity()));

        List<BrandEntity> brandEntitiesLst = csvProductDTOs.stream()
                .filter(filter -> existBrand(filter, brandEntityMap)
                ).map(it -> {
                    BrandEntity brandEntity = new BrandEntity();
                    brandEntity.setName(it.getBrand());
                    brandEntity.setBrandUrl(it.getBrandUrl());
                    return brandEntity;
                }).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(brandEntitiesLst)) {
            brandRepository.saveAll(brandEntitiesLst);
        }
    }

    @Override
    public boolean existBrand(CSVProductDTO csvProductDTO, Map<String, BrandEntity> brandEntityMap) {
        boolean isNotExist = brandEntityMap.get(csvProductDTO.getBrand()) == null && !csvProductDTO.getBrand().equals("");
        if(isNotExist){
            brandEntityMap.put(csvProductDTO.getBrand(), new BrandEntity());
        }
        return isNotExist;
    }
}