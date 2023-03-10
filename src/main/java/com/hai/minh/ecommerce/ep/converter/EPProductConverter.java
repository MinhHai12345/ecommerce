package com.hai.minh.ecommerce.ep.converter;

import com.hai.minh.ecommerce.entities.ProductEntity;
import com.hai.minh.ecommerce.ep.dtos.products.EPProductDto;

public interface EPProductConverter {

    EPProductDto convertToEpProductData(final ProductEntity productEntity);
}