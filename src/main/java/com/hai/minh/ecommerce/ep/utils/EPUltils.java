package com.hai.minh.ecommerce.ep.utils;

import com.hai.minh.ecommerce.ep.config.EPConfigProperties;
import com.hai.minh.ecommerce.ep.dtos.model.request.EPRequestAccessToken;
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
    public HttpHeaders buildHeadersForAuthen() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add(epConfigProperties.getStoreIdHeader(), epConfigProperties.getStoreId());
        return headers;
    }
}