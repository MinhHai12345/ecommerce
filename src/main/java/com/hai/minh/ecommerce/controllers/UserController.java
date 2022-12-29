package com.hai.minh.ecommerce.controllers;

import com.hai.minh.ecommerce.controllers.commons.AbstractController;
import com.hai.minh.ecommerce.dtos.UserDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
@Api(value = "API for User")
public class UserController extends AbstractController {
    private static final String URI = "/user";

    @PostMapping(value = URI)
    @ApiOperation(value = "Create user", response = ResponseEntity.class)
    public ResponseEntity<Map<String, Object>> create(@RequestBody final UserDTO userDTO) {
        return success(null);
    }


}
