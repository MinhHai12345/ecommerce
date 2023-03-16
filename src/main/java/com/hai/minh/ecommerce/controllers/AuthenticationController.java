package com.hai.minh.ecommerce.controllers;

import com.hai.minh.ecommerce.controllers.commons.AbstractController;
import com.hai.minh.ecommerce.dtos.login.request.LoginRequest;
import com.hai.minh.ecommerce.services.AuthenticationService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

@RestController
@Api(value = "API for Authentication")
public class AuthenticationController extends AbstractController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping(value = "/authenticate")
    public ResponseEntity<Map<String, Object>> createAuthenticationToken(@RequestBody @Valid LoginRequest request) {
        return this.success(authenticationService.login(request));
    }
}
