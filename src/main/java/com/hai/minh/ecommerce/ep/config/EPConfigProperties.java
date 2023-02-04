package com.hai.minh.ecommerce.ep.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Data
@Component
public class EPConfigProperties {
    @Value("${app.elasticpath.url}")
    private String epPath;
    @Value("${app.elasticpath.urlV2}")
    private String epPathV2;
    @Value("${app.elasticpath.clientID}")
    private String clientId;
    @Value("${app.elasticpath.clientSecret}")
    private String clientSecret;
    @Value("${app.elasticpath.storeID}")
    private String storeId;
    @Value("${app.grand-type}")
    private String grantType;
    private HttpHeaders headers;
    private Long expireAt;
}