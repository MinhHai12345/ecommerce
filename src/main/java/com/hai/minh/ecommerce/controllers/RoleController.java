package com.hai.minh.ecommerce.controllers;


import com.hai.minh.ecommerce.controllers.commons.AbstractController;
import com.hai.minh.ecommerce.dtos.RoleDTO;
import com.hai.minh.ecommerce.services.RoleService;
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
@RequestMapping("/api/roles")
@Api(value = "API for Role")
public class RoleController extends AbstractController {

    @Resource
    private RoleService roleService;

    @PostMapping
    @ApiOperation(value = "Create or update role", response = ResponseEntity.class)
    public ResponseEntity<Map<String, Object>> createOrUpdate(@RequestBody @Valid final RoleDTO role) {
        return success(roleService.createOrUpdate(role));
    }
}
