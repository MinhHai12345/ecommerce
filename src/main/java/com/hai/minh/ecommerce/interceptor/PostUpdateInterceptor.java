package com.hai.minh.ecommerce.interceptor;

public interface PostUpdateInterceptor<T> extends Interceptor {
    void onPostUpdate(T entity);
}
