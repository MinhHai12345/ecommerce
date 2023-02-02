package com.hai.minh.ecommerce.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.task.AsyncListenableTaskExecutor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfiguration {
    @Value("${rest-template.connect-timeout}")
    private int connectTimeout;
    @Value("${rest-template.read-timeout}")
    private int readTimeout;

    @Bean
    @Primary
    public RestTemplate customRestTemplate(){
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(connectTimeout);
        requestFactory.setReadTimeout(readTimeout);
        requestFactory.setTaskExecutor(taskExecutor());
        return new RestTemplate(requestFactory);
    }

    @Bean
    public AsyncListenableTaskExecutor taskExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(4);
        executor.setMaxPoolSize(4);
        executor.setQueueCapacity(200);
        executor.setThreadNamePrefix("task_executor_thread");
        executor.initialize();
        return executor;
    }
}