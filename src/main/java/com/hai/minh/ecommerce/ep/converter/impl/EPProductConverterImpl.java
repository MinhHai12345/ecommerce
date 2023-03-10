package com.hai.minh.ecommerce.ep.converter.impl;

import com.hai.minh.ecommerce.entities.ProductEntity;
import com.hai.minh.ecommerce.ep.converter.EPProductConverter;
import com.hai.minh.ecommerce.ep.dtos.products.EPProductDto;
import com.hai.minh.ecommerce.ep.dtos.products.EPProductPrice;
import com.hai.minh.ecommerce.ep.dtos.common.constants.EPConstants;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;

@Component
public class EPProductConverterImpl implements EPProductConverter {

    @Override
    public EPProductDto convertToEpProductData(ProductEntity productEntity) {
        if (productEntity == null) {
            return null;
        }

        final EPProductDto epProductDto = new EPProductDto();
        epProductDto.setCommodityType(EPConstants.PHYSICAL);
        epProductDto.setDescription(EPConstants.NO_DESCRIPTION);
        epProductDto.setDeleted(false);
        final EPProductPrice epProductPrice = new EPProductPrice();
        epProductPrice.setAmount(productEntity.getPrice().multiply(new BigDecimal(100)));
        epProductPrice.setCurrency(EPConstants.USD);
        epProductPrice.setIncludesTax(false);
        epProductDto.setPrice(Collections.singletonList(epProductPrice));
        epProductDto.setName(productEntity.getName());
        epProductDto.setSku(productEntity.getSku());
        epProductDto.setType(EPConstants.PRODUCT);
        epProductDto.setStatus(EPConstants.LIVE);
        epProductDto.setSlug(String.valueOf(new Date().getTime()));
        epProductDto.setManageStock(true);

        return epProductDto;
    }
}