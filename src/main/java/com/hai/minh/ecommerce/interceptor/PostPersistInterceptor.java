package com.hai.minh.ecommerce.interceptor;

public interface PostPersistInterceptor<T> extends Interceptor {
    void onPostPersist(T entity);
}
