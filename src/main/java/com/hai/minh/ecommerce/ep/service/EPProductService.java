package com.hai.minh.ecommerce.ep.service;

import com.hai.minh.ecommerce.ep.dtos.products.EPProductDto;
import com.hai.minh.ecommerce.ep.dtos.common.EPData;

public interface EPProductService {
    EPData<EPProductDto> createEPProduct(EPData<EPProductDto> request);
}