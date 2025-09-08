package com.hai.minh.ecommerce.interceptor;

import jakarta.annotation.Resource;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.ApplicationContext;

@Configurable(preConstruction = true)
public class InterceptingEntityListener {
    @Resource
    private ApplicationContext context;

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
