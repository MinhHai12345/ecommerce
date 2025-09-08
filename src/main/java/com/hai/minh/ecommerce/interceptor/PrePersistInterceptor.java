package com.hai.minh.ecommerce.interceptor;

public interface PrePersistInterceptor<T> extends Interceptor {
    void onPrePersist(T entity);
}
