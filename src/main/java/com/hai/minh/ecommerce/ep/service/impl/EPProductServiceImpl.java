package com.hai.minh.ecommerce.ep.service.impl;

import com.hai.minh.ecommerce.commons.ResponseData;
import com.hai.minh.ecommerce.ep.dtos.common.EPData;
import com.hai.minh.ecommerce.ep.config.EPConfigProperties;
import com.hai.minh.ecommerce.ep.dtos.EPProductDto;
import com.hai.minh.ecommerce.ep.dtos.common.constants.EPConstants;
import com.hai.minh.ecommerce.ep.service.EPProductService;
import com.hai.minh.ecommerce.ep.utils.EPUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class EPProductServiceImpl implements EPProductService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private EPUtils epUltils;

    @Autowired
    private EPConfigProperties configProperties;

    @Override
    public ResponseData<EPProductDto> createEPProduct(EPData<EPProductDto> request) {
        log.info("START PROCESS CREATE EP PRODUCT");
        ResponseData<EPProductDto> responseData = new ResponseData<>();
        try {
            if(request != null){
                final HttpHeaders headers = epUltils.buildHeaders();
                final HttpEntity<EPData<EPProductDto>> entity = new HttpEntity<>(request, headers);

                final String url = configProperties.getEpPathV2() + EPConstants.PRODUCT_URL;
                final ResponseEntity<EPData<EPProductDto>> responseEntity = restTemplate
                        .exchange(url, HttpMethod.POST, entity, new ParameterizedTypeReference<EPData<EPProductDto>>() {
                        });
                log.info("END PROCESS CREATE EP PRODUCT");
            }
        } catch (RestClientException e) {
            log.error("MESSAGE: {}" + e.getMessage());
        }
        return null;
    }
}