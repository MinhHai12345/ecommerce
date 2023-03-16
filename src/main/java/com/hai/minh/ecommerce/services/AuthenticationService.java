package com.hai.minh.ecommerce.services;

import com.hai.minh.ecommerce.dtos.login.request.LoginRequest;
import com.hai.minh.ecommerce.dtos.login.response.LoginResponse;

public interface AuthenticationService {

    LoginResponse login(final LoginRequest request);

}
