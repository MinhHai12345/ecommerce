package com.hai.minh.ecommerce.interceptor;

public interface PostRemoveInterceptor<T> extends Interceptor {
    void onPostRemove(T entity);
}

