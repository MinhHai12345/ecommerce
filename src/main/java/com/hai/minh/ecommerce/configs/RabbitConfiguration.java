package com.hai.minh.ecommerce.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hai.minh.ecommerce.commons.constants.RabbitConstants;
import com.hai.minh.ecommerce.configs.props.RabbitConfigProperties;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@EnableRabbit
@Configuration
public class RabbitConfiguration {

    @Autowired
    private RabbitConfigProperties rabbitProperties;

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setAddresses(rabbitProperties.getAddresses() + ":" + rabbitProperties.getPort());
        connectionFactory.setUsername(rabbitProperties.getUsername());
        connectionFactory.setPassword(rabbitProperties.getPassword());
        connectionFactory.setPublisherConfirms(true);
        return connectionFactory;
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public RabbitTemplate rabbitTemplateNew(ObjectMapper mapper) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        template.setMessageConverter(new Jackson2JsonMessageConverter(mapper));
        return template;
    }
    @Bean
    public Queue createQueue() {
        return new Queue(RabbitConstants.CREATE_PRODUCT_QUEUE, true);
    }

    @Bean
    public Exchange diectExchange() {
        return new DirectExchange(RabbitConstants.EXCHANGE_NAME, true, false);
    }

    @Bean
    public Binding queueBinding() {
        return new Binding(RabbitConstants.CREATE_PRODUCT_QUEUE, Binding.DestinationType.QUEUE,
                RabbitConstants.EXCHANGE_NAME, "", null);
    }
}