package com.hai.minh.ecommerce.ep.service;

import com.hai.minh.ecommerce.entities.ProductEntity;
import com.hai.minh.ecommerce.ep.dtos.EPProductDto;

import java.util.List;

public interface EPProductService {
    EPProductDto createEPProduct(List<ProductEntity> productEntities);
}