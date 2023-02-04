package com.hai.minh.ecommerce.ep.converter.Impl;

import com.hai.minh.ecommerce.entities.ProductEntity;
import com.hai.minh.ecommerce.ep.common.EpData;
import com.hai.minh.ecommerce.ep.converter.EPProductConverter;
import com.hai.minh.ecommerce.ep.dtos.EPProductDto;
import com.hai.minh.ecommerce.ep.dtos.EPProductPrice;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;

@Component("epProductConverterImpl")
public class EPProductConverterImpl implements EPProductConverter {
    public static final String PHYSICAL = "physical";

    @Override
    public EpData<EPProductDto> convertToEpProductData(ProductEntity productEntity) {
        final EPProductDto epProductDto = new EPProductDto();
        epProductDto.setCommodityType(PHYSICAL);
        epProductDto.setDescription("NO DESCRIPTION");
        epProductDto.setIsDeleted(false);
        final EPProductPrice epProductPrice = new EPProductPrice();
        epProductPrice.setAmount(productEntity.getPrice().multiply(new BigDecimal(100)));
        epProductPrice.setCurrency("USD");
        epProductPrice.setIncludesTax(false);
        epProductDto.setPrice(Collections.singletonList(epProductPrice));
        epProductDto.setName(productEntity.getName());
        epProductDto.setSku(productEntity.getSku());
        epProductDto.setType("product");
        epProductDto.setStatus("live");
        epProductDto.setSlug(String.valueOf(new Date().getTime()));
        epProductDto.setManageStock(true);
        final EpData<EPProductDto> epData = new EpData<>();
        epData.setData(epProductDto);
        return epData;
    }
}