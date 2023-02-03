package com.hai.minh.ecommerce.ep.service.Impl;

import com.hai.minh.ecommerce.entities.ProductEntity;
import com.hai.minh.ecommerce.ep.dtos.EPProductDto;
import com.hai.minh.ecommerce.ep.service.EPProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class EPProductServiceImpl implements EPProductService {
    @Override
    @Retryable( value = Exception.class,
            maxAttempts = 4, backoff = @Backoff(delay = 100))
    public EPProductDto createEPProduct(List<ProductEntity> productEntities) {
        return null;
    }
}