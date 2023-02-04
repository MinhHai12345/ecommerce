package com.hai.minh.ecommerce.ep.service.impl;

import com.hai.minh.ecommerce.ep.config.EPConfigProperties;
import com.hai.minh.ecommerce.ep.dtos.EPToken;
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

import static com.hai.minh.ecommerce.ep.utils.EPStringUtils.*;

@Slf4j
@Service
public class EPAccessTokenServiceImpl implements EPAccessTokenService {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private EPConfigProperties configProperties;
    @Override
    public String fetchToken() {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add(STORE_ID, configProperties.getStoreId());

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add(CLIENT_ID_HEADER, configProperties.getClientId());
        body.add(CLIENT_SECRET, configProperties.getClientSecret());
        body.add(GRANT_TYPE, configProperties.getGrantType());

        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(body, headers);
        String url = configProperties.getEpPath() + OAUTH + ACCESS_TOKEN;
        try{
            EPToken token = restTemplate.exchange(url, HttpMethod.POST, entity,
                    new ParameterizedTypeReference<EPToken>() {
                    }).getBody();
            if(token.getAccessToken() != null && StringUtils.isNotEmpty(token.getAccessToken())){
                configProperties.getHeaders().setBearerAuth(token.getAccessToken());
                log.info("Access Token : {}", token.getAccessToken());
                configProperties.setExpireAt(token.getExpiresAt());
                return token.getAccessToken();
            }
        }catch (Exception e){
            log.error(this.getClass().toString().concat("Request access token {} ".concat(e.getMessage())));
        }
        return StringUtils.EMPTY;
    }
}