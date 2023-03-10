package com.hai.minh.ecommerce.configs;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.annotation.Resource;

@Configuration
public class RabbitConfiguration {
    private static final String EP_PRODUCT_QUEUE_NAME = "ep-product";

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
        return rabbitTemplate;
    }

//    @Bean
//    SimpleMessageListenerContainer productContainer(@Qualifier(value = "rabbitConnectionFactory") ConnectionFactory rabbitConnectionFactory,
//            final MessageListenerAdapter productListenerAdapter) {
//        final SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(rabbitConnectionFactory);
//        container.setQueueNames(EP_PRODUCT_QUEUE_NAME);
//        container.setMessageListener(productListenerAdapter);
//        return container;
//    }
//
//    @Bean
//    MessageListenerAdapter productListenerAdapter(final BrandService receiver, final MessageConverter converter) {
//        final MessageListenerAdapter messageListenerAdapter = new MessageListenerAdapter(receiver);
//        messageListenerAdapter.setMessageConverter(converter);
//        return messageListenerAdapter;
//    }

}