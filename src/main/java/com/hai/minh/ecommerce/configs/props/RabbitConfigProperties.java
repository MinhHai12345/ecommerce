package com.hai.minh.ecommerce.configs.props;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class RabbitConfigProperties {
    @Value("${rabbitmq.host}")
    private String addresses;

    @Value("${rabbitmq.port}")
    private String port;

    @Value("${rabbitmq.username}")
    private String username;

    @Value("${rabbitmq.password}")
    private String password;

    @Value("${rabbitmq.queue.create-product-queue}")
    private String queueCreateProduct;
}