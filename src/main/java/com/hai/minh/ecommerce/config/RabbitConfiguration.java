package com.hai.minh.ecommerce.config;

import jakarta.annotation.Resource;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class RabbitConfiguration {
    @Resource
    private RabbitProperties rabbitProperties;

    @Bean
    @Primary
    public ConnectionFactory rabbitConnectionFactory() {
        CachingConnectionFactory rabbitConnectionFactory = new CachingConnectionFactory();
        rabbitConnectionFactory.setAddresses(rabbitProperties.getAddresses());
        rabbitConnectionFactory.setUsername(rabbitProperties.getUsername());
        rabbitConnectionFactory.setPassword(rabbitProperties.getPassword());
        rabbitConnectionFactory.setPort(rabbitProperties.getPort());
        // Optional: Set virtual host if you use one
        if (rabbitProperties.getVirtualHost() != null) {
            rabbitConnectionFactory.setVirtualHost(rabbitProperties.getVirtualHost());
        }
        rabbitConnectionFactory.setPublisherConfirmType(CachingConnectionFactory.ConfirmType.CORRELATED);
        rabbitConnectionFactory.setPublisherReturns(true);
        return rabbitConnectionFactory;
    }

    @Bean
    public Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(this.rabbitConnectionFactory());
        rabbitTemplate.setMessageConverter(this.converter());
        rabbitTemplate.setMandatory(true);
        return rabbitTemplate;
    }


}