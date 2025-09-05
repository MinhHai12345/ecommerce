package com.hai.minh.ecommerce.modules.auth.service;

import com.hai.minh.ecommerce.modules.auth.model.request.LoginRequest;
import com.hai.minh.ecommerce.modules.auth.model.response.LoginResponse;

public interface AuthenticationService {

    LoginResponse login(final LoginRequest request);

}
