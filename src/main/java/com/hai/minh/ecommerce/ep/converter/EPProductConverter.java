package com.hai.minh.ecommerce.ep.converter;

import com.hai.minh.ecommerce.entities.ProductEntity;
import com.hai.minh.ecommerce.ep.dtos.EPProductDto;
import com.hai.minh.ecommerce.ep.dtos.common.EPData;

public interface EPProductConverter {
    EPData<EPProductDto> convertToEpProductData(final ProductEntity productEntity);
}