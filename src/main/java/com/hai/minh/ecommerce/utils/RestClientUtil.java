package com.hai.minh.ecommerce.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hai.minh.ecommerce.commons.constants.ErrorConstants;
import com.hai.minh.ecommerce.exceptions.CustomHttpServerErrorException;
import com.hai.minh.ecommerce.exceptions.CustomRestClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StopWatch;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public final class RestClientUtil {
    private final Logger log = LoggerFactory.getLogger(RestClientUtil.class);

    @Autowired
    private RestTemplate restTemplate;

    private static class RestClientHelper {
        private static final RestClientUtil INSTANCE = new RestClientUtil();
    }

    public static RestClientUtil getInstance() {
        return RestClientHelper.INSTANCE;
    }

    private static class HttpHeaderBuilder {
        private static final String CONTEXT_ID = "ContextId";
        private static final String BRANCH = "Branch";

        private final HttpHeaders httpHeaders;

        public HttpHeaderBuilder() {
            this.httpHeaders = new HttpHeaders();
        }

        public HttpHeaderBuilder setContentType(MediaType mediaType) {
            this.httpHeaders.setContentType(mediaType);
            return this;
        }

        public HttpHeaderBuilder addAgilityHeader(String contextId, String initialBranch) {
            this.httpHeaders.add(CONTEXT_ID, contextId);
            this.httpHeaders.add(BRANCH, initialBranch);
            return this;
        }

        public HttpHeaders build() {
            return this.httpHeaders;
        }
    }

    public <S, R> ResponseEntity<R> callRest(final HttpMethod httpMethod, final String url,
                                             final HttpHeaders httpHeaders, final S requestData,
                                             final Class<R> responseType) {
        try {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            HttpEntity<?> requestEntity = this.getHttpEntity(httpHeaders, requestData);
            ResponseEntity<R> responseEntity = restTemplate.exchange(url, httpMethod,
                    requestEntity, responseType);
            stopWatch.stop();
            this.logRequestInformation(httpMethod.name(), url, stopWatch.getTotalTimeSeconds(), requestData);
            return responseEntity;
        } catch (HttpClientErrorException ex) {
            if (HttpStatus.UNAUTHORIZED.equals(ex.getStatusCode())) {
                throw new CustomRestClientException(ErrorConstants.ERR_UNAUTHORIZED, "Invalid User ID or Password.");
            }
            throw new CustomRestClientException(ex.getMessage());
        } catch (HttpServerErrorException ex) {
            this.log.error("::::: Error rest client call api with url='{}'", url);
            throw new CustomHttpServerErrorException(ex.getStatusCode(), ex.getStatusText(),
                    ex.getResponseHeaders(), ex.getResponseBodyAsByteArray(), null, ex.getMessage());
        } catch (final Exception ex) {
            this.log.error("::::: Error rest client call api with url='{}'", url);
            throw new CustomRestClientException(ex.getMessage());
        }
    }

    private <S> HttpEntity<?> getHttpEntity(final HttpHeaders httpHeaders, final S requestData) {
        HttpEntity<?> requestEntity = new HttpEntity<>(requestData, httpHeaders);
        if (httpHeaders != null && requestData != null) {
            final MediaType contentType = httpHeaders.getContentType();
            if (MediaType.MULTIPART_FORM_DATA.equals(contentType) || MediaType.APPLICATION_FORM_URLENCODED.equals(contentType)) {
                restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
                requestEntity = new HttpEntity<>(this.convert2RequestParameters(requestData), httpHeaders);
            }
        }
        return requestEntity;
    }

    private <S> MultiValueMap<String, Object> convert2RequestParameters(final S requestData) {
        MultiValueMap<String, Object> requestParameters = null;
        if (requestData != null) {
            if (requestData instanceof MultiValueMap) {
                return (MultiValueMap<String, Object>) requestData;
            }
            requestParameters = new LinkedMultiValueMap<>();
            final ObjectMapper mapper = new ObjectMapper();
            final Map<String, Object> mapData = mapper.convertValue(requestData,
                    new TypeReference<HashMap<String, Object>>() {
                    });
            for (final Map.Entry<String, Object> item : mapData.entrySet()) {
                final Object value = item.getValue();
                requestParameters.add(item.getKey(),
                        value instanceof Boolean ? String.valueOf(value) : value);
            }
        }
        return requestParameters;
    }

    private void logRequestInformation(final String methodName, final String uri, final double durationTimes, final Object payload) {
        log.info("Rest client call api with method name='{}', url='{}', duration = {}(s), payload={}", methodName, uri, durationTimes, payload);
    }

    private HttpHeaders getHttpHeaderAgility(String contextId, String branchId) {
        return new HttpHeaderBuilder()
                .setContentType(MediaType.APPLICATION_JSON)
                .addAgilityHeader(contextId, branchId)
                .build();
    }

}
