package com.hai.minh.ecommerce.ep.converter.impl;

import com.hai.minh.ecommerce.entities.ProductEntity;
import com.hai.minh.ecommerce.ep.dtos.common.EPData;
import com.hai.minh.ecommerce.ep.converter.EPProductConverter;
import com.hai.minh.ecommerce.ep.dtos.EPProductDto;
import com.hai.minh.ecommerce.ep.dtos.EPProductPrice;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;

import static com.hai.minh.ecommerce.ep.dtos.common.constants.EPConstants.*;

@Component
public class EPProductConverterImpl implements EPProductConverter {

    @Override
    public EPData<EPProductDto> convertToEpProductData(ProductEntity productEntity) {
        final EPProductDto epProductDto = new EPProductDto();
        epProductDto.setCommodityType(PHYSICAL);
        epProductDto.setDescription(NO_DESCRIPTION);
        epProductDto.setIsDeleted(false);
        final EPProductPrice epProductPrice = new EPProductPrice();
        epProductPrice.setAmount(productEntity.getPrice().multiply(new BigDecimal(100)));
        epProductPrice.setCurrency(USD);
        epProductPrice.setIncludesTax(false);
        epProductDto.setPrice(Collections.singletonList(epProductPrice));
        epProductDto.setName(productEntity.getName());
        epProductDto.setSku(productEntity.getSku());
        epProductDto.setType(PRODUCT);
        epProductDto.setStatus(LIVE);
        epProductDto.setSlug(String.valueOf(new Date().getTime()));
        epProductDto.setManageStock(true);
        final EPData<EPProductDto> epData = new EPData<>();
        epData.setData(epProductDto);
        return epData;
    }
}