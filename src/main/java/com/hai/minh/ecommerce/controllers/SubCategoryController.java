package com.hai.minh.ecommerce.controllers;


import com.hai.minh.ecommerce.controllers.commons.AbstractController;
import com.hai.minh.ecommerce.services.SubCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/api/sub-categories")
@Api(value = "API for sub category")
@RequiredArgsConstructor
public class SubCategoryController extends AbstractController {

    private final SubCategoryService subCategoryService;

    @PostMapping(value = "/import")
    @ApiOperation(value = "Import sub categories", response = ResponseEntity.class)
    public ResponseEntity<Map<String, Object>> importSubCategories(@RequestParam("file") final MultipartFile file) {
        return success(subCategoryService.importSubCategories(file));
    }
}
