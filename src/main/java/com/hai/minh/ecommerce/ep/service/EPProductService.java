package com.hai.minh.ecommerce.ep.service;

import com.hai.minh.ecommerce.commons.ResponseData;
import com.hai.minh.ecommerce.ep.common.EpData;
import com.hai.minh.ecommerce.ep.dtos.EPProductDto;

public interface EPProductService {
    ResponseData<EPProductDto> createEPProduct(EpData<EPProductDto> request);
}