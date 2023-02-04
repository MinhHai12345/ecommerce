package com.hai.minh.ecommerce.ep.utils;

import com.hai.minh.ecommerce.ep.config.EPConfigProperties;
import com.hai.minh.ecommerce.ep.service.EPAccessTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class EPUltils {

    @Autowired
    private EPConfigProperties epConfigProperties;
    @Autowired
    private EPAccessTokenService accessTokenService;

    public HttpHeaders buildHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessTokenService.getToken());
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        epConfigProperties.setHeaders(headers);
        return headers;
    }
}