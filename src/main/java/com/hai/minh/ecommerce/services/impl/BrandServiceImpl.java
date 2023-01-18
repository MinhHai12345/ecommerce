package com.hai.minh.ecommerce.services.impl;

import com.hai.minh.ecommerce.dtos.products.CSVProductDTO;
import com.hai.minh.ecommerce.entities.BrandEntity;
import com.hai.minh.ecommerce.repository.BrandRepository;
import com.hai.minh.ecommerce.services.BrandService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;

    @Autowired
    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Transactional
    @Override
    public List<BrandEntity> saveBrandWithCSV(List<CSVProductDTO> csvProductDTO) {

        Set<String> brandNames = csvProductDTO.stream()
                .map(CSVProductDTO::getBrand)
                .collect(Collectors.toSet());
        List<BrandEntity> brands = brandRepository.findByNameIn(brandNames);

        Map<String, BrandEntity> brandsMap = brands.stream()
                .collect(Collectors.toMap(BrandEntity::getName, Function.identity()));
        List<BrandEntity> brandsList = csvProductDTO.stream()
                .filter(filter -> existBrand(filter, brandsMap))
                .map(item -> {
                    BrandEntity brandEntity = new BrandEntity();
                    brandEntity.setName(item.getBrand());
                    brandEntity.setBrandUrl(item.getBrandUrl());
                    return brandEntity;
                }).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(brandsList)) {
            return brandRepository.saveAll(brandsList);
        }
        return Collections.emptyList();
    }

    @Override
    public boolean existBrand(CSVProductDTO csvProductDTO, Map<String, BrandEntity> brandEntityMap) {
        boolean isNotExist = brandEntityMap.get(csvProductDTO.getBrand()) == null
                && StringUtils.isNotEmpty(csvProductDTO.getBrand());
        if (isNotExist) {
            brandEntityMap.put(csvProductDTO.getBrand(), new BrandEntity());
        }
        return isNotExist;
    }
}