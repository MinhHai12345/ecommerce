package com.hai.minh.ecommerce.ep.service;

import com.hai.minh.ecommerce.ep.dtos.EPToken;

public interface EPAccessTokenService {
    EPToken fetchToken();
    EPToken getToken();
}