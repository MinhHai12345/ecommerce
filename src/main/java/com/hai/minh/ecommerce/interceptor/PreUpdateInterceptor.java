package com.hai.minh.ecommerce.interceptor;

public interface PreUpdateInterceptor<T> extends Interceptor {
    void onPreUpdate(T entity);
}
