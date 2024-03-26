package com.hai.minh.ecommerce.controllers;

import com.hai.minh.ecommerce.controllers.commons.AbstractController;
import com.hai.minh.ecommerce.services.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/api/categories")
@Api(value = "API for category")
public class CategoryController extends AbstractController {

    @Resource
    private CategoryService categoryService;

    @PostMapping(value = "/import")
    @ApiOperation(value = "Import categories", response = ResponseEntity.class)
    public ResponseEntity<Map<String, Object>> importCategories(@RequestParam("file") final MultipartFile file) {
        return success(categoryService.importCategories(file));
    }
}
