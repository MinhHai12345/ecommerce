package com.hai.minh.ecommerce.controllers;

import com.hai.minh.ecommerce.controllers.commons.AbstractController;
import com.hai.minh.ecommerce.dtos.UserDTO;
import com.hai.minh.ecommerce.dtos.login.request.ResetPasswordDTO;
import com.hai.minh.ecommerce.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@Api(value = "API for User")
public class UserController extends AbstractController {
    @Resource
    private UserService userService;

    @PostMapping(value = {"", "/register"})
    @ApiOperation(value = "Create or update user", response = ResponseEntity.class)
    public ResponseEntity<Map<String, Object>> createOrUpdate(@RequestBody @Valid final UserDTO userDTO) {
        return success(userService.createOrUpdate(userDTO));
    }

    @PostMapping(value = "/reset-password")
    @ApiOperation(value = "Reset password for user", response = ResponseEntity.class)
    public ResponseEntity<Map<String, Object>> resetPassword(@RequestBody @Valid final ResetPasswordDTO request) {
        return success(userService.resetPassword(request));
    }

}
