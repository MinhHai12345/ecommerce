package com.hai.minh.ecommerce.configs;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class ConfigProperties {
    @Value("${app.elasticpath.url}")
    private String epUrl;

    @Value("${app.elasticpath.clientID}")
    private String clientId;

    @Value("${app.elasticpath.clientSecret}")
    private String clientSecret;

    @Value("${app.elasticpath.storeID}")
    private String storeId;
}