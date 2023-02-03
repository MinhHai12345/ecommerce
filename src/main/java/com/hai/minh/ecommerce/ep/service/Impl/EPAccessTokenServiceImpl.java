package com.hai.minh.ecommerce.ep.service.Impl;

import com.hai.minh.ecommerce.ep.config.EPConfigProperties;
import com.hai.minh.ecommerce.ep.dtos.EPToken;
import com.hai.minh.ecommerce.ep.service.EPAccessTokenService;
import com.hai.minh.ecommerce.ep.utils.EPUltils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.atomic.AtomicReference;

import static com.hai.minh.ecommerce.ep.utils.StringUltils.ACCESS_TOKEN;
import static com.hai.minh.ecommerce.ep.utils.StringUltils.OAUTH;

@Slf4j
@Service
public class EPAccessTokenServiceImpl implements EPAccessTokenService {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private EPConfigProperties configProperties;
    @Autowired
    private EPUltils epUltils;

    private final AtomicReference<EPToken> atomicRefToken = new AtomicReference<>(EPToken.expiredToken());

    @Override
    public EPToken fetchToken() {
        HttpHeaders headers = epUltils.buildHeadersForAuthen();
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add(configProperties.getClientIdHeader(), configProperties.getClientId());
        body.add(configProperties.getClientSecretHeader(), configProperties.getClientSecret());
        body.add(configProperties.getGrantTypeHeader(), configProperties.getGrantType());

        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(body, headers);
        String url = configProperties.getEpPath() + OAUTH + ACCESS_TOKEN;

        try{
            EPToken token = restTemplate.exchange(url, HttpMethod.POST, entity,
                    new ParameterizedTypeReference<EPToken>() {
                    }).getBody();
            if(StringUtils.isNotEmpty(token.getAccessToken())){
                atomicRefToken.set(token);
                return token;
            }
        }catch (Exception e){
            log.error(this.getClass().toString().concat("Request access token {} ".concat(e.getMessage())));
        }
        return null;
    }

    @Override
    public EPToken getToken() {
        if (atomicRefToken.get().isExpired()) {
            atomicRefToken.set(this.fetchToken());
        }
        return atomicRefToken.get();
    }
}