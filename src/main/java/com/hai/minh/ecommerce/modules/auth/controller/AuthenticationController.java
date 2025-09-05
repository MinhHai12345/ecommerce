package com.hai.minh.ecommerce.modules.auth.controller;

import com.hai.minh.ecommerce.modules.auth.model.request.LoginRequest;
import com.hai.minh.ecommerce.modules.auth.model.response.LoginResponse;
import com.hai.minh.ecommerce.modules.auth.service.AuthenticationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Authentication", description = "API for Authentication")
public class AuthenticationController {

    @Resource
    private AuthenticationService authenticationService;

    @PostMapping(value = "/authenticate")
    public LoginResponse createAuthenticationToken(@RequestBody @Valid LoginRequest request) {
        return authenticationService.login(request);
    }
}
