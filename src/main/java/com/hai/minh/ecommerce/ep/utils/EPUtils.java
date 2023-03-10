package com.hai.minh.ecommerce.ep.utils;

import com.hai.minh.ecommerce.configs.properties.AppProperties;
import com.hai.minh.ecommerce.ep.service.EPAccessTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Collections;

@Component
public class EPUtils {

    @Autowired
    private AppProperties appProperties;

    @Autowired
    private EPAccessTokenService accessTokenService;

    public HttpHeaders buildHeaders() {
        if (appProperties.getElasticPath().getExpireAt() == null
                || Instant.now().getEpochSecond() > appProperties.getElasticPath().getExpireAt()) {
            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(accessTokenService.fetchToken());
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.setContentType(MediaType.APPLICATION_JSON);
            appProperties.getElasticPath().setHeaders(headers);
            return headers;
        }
        return appProperties.getElasticPath().getHeaders();
    }
}