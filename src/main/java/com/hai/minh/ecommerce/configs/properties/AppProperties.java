package com.hai.minh.ecommerce.configs.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app")
public class AppProperties {

    private final ElasticPath elasticPath = new ElasticPath();

    private final Jwt jwt = new Jwt();

    public ElasticPath getElasticPath() {
        return elasticPath;
    }

    public Jwt getJwt() {
        return jwt;
    }
}
