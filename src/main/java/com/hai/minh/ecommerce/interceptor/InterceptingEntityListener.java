package com.hai.minh.ecommerce.interceptor;

import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.ApplicationContext;

@RequiredArgsConstructor
@Configurable(preConstruction = true)
public class InterceptingEntityListener {
    private final ApplicationContext context;

    private EntityInterceptor entityInterceptor;

    @PrePersist
    public void prePersist(Object entity) {
        getInterceptor().onPrePersist(entity);
    }

    @PostPersist
    public void postPersist(Object entity) {
        getInterceptor().onPostPersist(entity);
    }

    @PreUpdate
    public void preUpdate(Object entity) {
        getInterceptor().onPreUpdate(entity);
    }

    @PostUpdate
    public void postUpdate(Object entity) {
        getInterceptor().onPostUpdate(entity);
    }

    @PostRemove
    public void postRemove(Object entity) {
        getInterceptor().onPostRemove(entity);
    }

    private EntityInterceptor getInterceptor() {
        if (entityInterceptor == null) {
            entityInterceptor = context.getBean(EntityInterceptor.class);
        }
        return entityInterceptor;
    }
}
