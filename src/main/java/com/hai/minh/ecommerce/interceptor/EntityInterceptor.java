package com.hai.minh.ecommerce.interceptor;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@SuppressWarnings("unchecked")
public class EntityInterceptor {

    @Resource
    private List<Interceptor> interceptors;

    public void onPrePersist(Object entity) {
        dispatch(entity, PrePersistInterceptor.class, i -> ((PrePersistInterceptor<Object>) i).onPrePersist(entity));
    }

    public void onPostPersist(Object entity) {
        dispatch(entity, PostPersistInterceptor.class, i -> ((PostPersistInterceptor<Object>) i).onPostPersist(entity));
    }

    public void onPreUpdate(Object entity) {
        dispatch(entity, PreUpdateInterceptor.class, i -> ((PreUpdateInterceptor<Object>) i).onPreUpdate(entity));
    }

    public void onPostUpdate(Object entity) {
        dispatch(entity, PostUpdateInterceptor.class, i -> ((PostUpdateInterceptor<Object>) i).onPostUpdate(entity));
    }

    public void onPostRemove(Object entity) {
        dispatch(entity, PostRemoveInterceptor.class, i -> ((PostRemoveInterceptor<Object>) i).onPostRemove(entity));
    }

    private <T> void dispatch(Object entity, Class<?> type, java.util.function.Consumer<T> action) {
        interceptors.stream()
                .filter(type::isInstance)
                .filter(i -> supports(i, entity))
                .forEach(i -> action.accept((T) i));
    }

    private boolean supports(Object interceptor, Object entity) {
        return Arrays.stream(interceptor.getClass().getGenericInterfaces())
                .anyMatch(type -> type.getTypeName().contains(entity.getClass().getSimpleName()));
    }
}
