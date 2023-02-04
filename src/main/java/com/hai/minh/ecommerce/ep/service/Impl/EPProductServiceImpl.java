package com.hai.minh.ecommerce.ep.service.Impl;

import com.hai.minh.ecommerce.commons.ResponseData;
import com.hai.minh.ecommerce.ep.common.EpData;
import com.hai.minh.ecommerce.ep.config.EPConfigProperties;
import com.hai.minh.ecommerce.ep.dtos.EPProductDto;
import com.hai.minh.ecommerce.ep.service.EPProductService;
import com.hai.minh.ecommerce.ep.utils.EPUltils;
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

import static com.hai.minh.ecommerce.ep.utils.EPStringUtils.PRODUCT_URL;

@Service
@Slf4j
public class EPProductServiceImpl implements EPProductService {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private EPUltils epUltils;
    @Autowired
    private EPConfigProperties configProperties;
    @Override
    public ResponseData<EPProductDto> createEPProduct(EpData<EPProductDto> request) {
        log.info("START");
        ResponseData<EPProductDto> responseData = new ResponseData<>();
        try {
            if(request != null){
                final HttpHeaders headers = epUltils.buildHeaders();
                final HttpEntity<EpData<EPProductDto>> entity = new HttpEntity<>(request, headers);

                final String url = configProperties.getEpPathV2() + PRODUCT_URL;
                final ResponseEntity<EpData<EPProductDto>> responseEntity = restTemplate
                        .exchange(url, HttpMethod.POST, entity, new ParameterizedTypeReference<EpData<EPProductDto>>() {
                        });
                log.info("DONE");
            }
        } catch (RestClientException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}