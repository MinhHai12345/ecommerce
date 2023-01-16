package com.hai.minh.ecommerce.services;

import com.hai.minh.ecommerce.dtos.products.CSVProductDTO;
import com.hai.minh.ecommerce.entities.BrandEntity;

import java.util.List;
import java.util.Map;

public interface BrandService {
    void saveBrandWithCSV(List<CSVProductDTO> csvProductDTOs);

    boolean existBrand(CSVProductDTO csvProductDTO, Map<String, BrandEntity> brandEntityMap);
}