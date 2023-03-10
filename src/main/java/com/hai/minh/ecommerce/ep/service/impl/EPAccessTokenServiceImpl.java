package com.hai.minh.ecommerce.ep.service.impl;

import com.hai.minh.ecommerce.configs.properties.AppProperties;
import com.hai.minh.ecommerce.configs.properties.ElasticPath;
import com.hai.minh.ecommerce.ep.dtos.auth.response.EPToken;
import com.hai.minh.ecommerce.ep.dtos.common.constants.EPConstants;
import com.hai.minh.ecommerce.ep.service.EPAccessTokenService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Slf4j
@Service
public class EPAccessTokenServiceImpl implements EPAccessTokenService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AppProperties appProperties;

    @Override
    public String fetchToken() {
        ElasticPath elasticPathProperties = appProperties.getElasticPath();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add(EPConstants.STORE_ID, elasticPathProperties.getStoreId());

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add(EPConstants.CLIENT_ID_HEADER, elasticPathProperties.getClientId());
        body.add(EPConstants.CLIENT_SECRET, elasticPathProperties.getClientSecret());
        body.add(EPConstants.GRANT_TYPE, elasticPathProperties.getGrandType());

        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(body, headers);
        String url = elasticPathProperties.getUrl() + EPConstants.OAUTH + EPConstants.ACCESS_TOKEN;
        try {
            EPToken token = restTemplate.exchange(url, HttpMethod.POST, entity,
                    new ParameterizedTypeReference<EPToken>() {
                    }).getBody();
            if (token != null && StringUtils.isNotEmpty(token.getAccessToken())) {
                log.info("Access Token : {}", token.getAccessToken());
                elasticPathProperties.setExpireAt(token.getExpiresAt());
                return token.getAccessToken();
            }
        } catch (Exception e) {
            log.error(this.getClass().toString().concat("Request access token {} ".concat(e.getMessage())));
        }
        return StringUtils.EMPTY;
    }
}