package com.hai.minh.ecommerce.configs;

import com.hai.minh.ecommerce.utils.RestClientUtil;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class CommonConfiguration {

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplateBuilder().build();
    }

    @Bean(name = "restClientUtil")
    public RestClientUtil restClientUtil() {
        return RestClientUtil.getInstance();
    }
}