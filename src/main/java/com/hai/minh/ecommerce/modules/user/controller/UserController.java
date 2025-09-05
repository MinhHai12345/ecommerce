package com.hai.minh.ecommerce.modules.user.controller;

import com.hai.minh.ecommerce.modules.auth.model.request.ResetPasswordDTO;
import com.hai.minh.ecommerce.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@Tag(name = "User", description = "API for User")
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping(value = {"", "/register"})
    @Operation(summary = "Create or update user")
    public UserData createOrUpdate(@RequestBody @Valid final UserData userDTO) {
        return userService.createOrUpdate(userDTO);
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Retrieve user by id")
    public UserData retrieveUser(@PathVariable("id") Integer id) {
        return userService.findById(id);
    }

    @PostMapping(value = "/reset-password")
    @Operation(summary = "Reset password for user")
    public boolean resetPassword(@RequestBody @Valid final ResetPasswordDTO request) {
        return userService.resetPassword(request);
    }

}
