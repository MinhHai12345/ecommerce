package com.hai.minh.ecommerce.third_party.agility.dtos.authen;

import java.util.concurrent.atomic.AtomicReference;

public class TokenInfoHolder {
    private static final AtomicReference<TokenInfoHolder> INSTANCE = new AtomicReference<>();
    private String contextId;

    public static TokenInfoHolder getInstance() {
        if (INSTANCE.get() == null) {
            synchronized (TokenInfoHolder.class) {
                if (INSTANCE.get() == null) {
                    INSTANCE.set(new TokenInfoHolder());
                }
            }
        }
        return INSTANCE.get();
    }

    public synchronized String getContextId() {
        return contextId;
    }

    public synchronized void setContextId(String contextId) {
        this.contextId = contextId;
    }

}
