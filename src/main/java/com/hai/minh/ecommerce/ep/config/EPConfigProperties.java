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
    @Value("${app.elasticpath.clientID}")
    private String clientId;
    @Value("${app.elasticpath.clientSecret}")
    private String clientSecret;
    @Value("${app.elasticpath.storeID}")
    private String storeId;
    @Value("${app.grand-type}")
    private String grantType;

    /** HEADER */
    @Value("${app.header.client-id}")
    private String clientIdHeader;
    @Value("${app.header.client-secret}")
    private String clientSecretHeader;
    @Value("${app.header.store-id}")
    private String storeIdHeader;
    @Value("${app.header.grant-type}")
    private String grantTypeHeader;

    private HttpHeaders headers;
}