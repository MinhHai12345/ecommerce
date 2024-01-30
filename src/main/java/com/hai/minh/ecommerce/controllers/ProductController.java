package com.hai.minh.ecommerce.controllers;

import com.hai.minh.ecommerce.controllers.commons.AbstractController;
import com.hai.minh.ecommerce.services.ProductService;
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
@RequestMapping("/api/products")
@Api(value = "API for Product")
@RequiredArgsConstructor
public class ProductController extends AbstractController {

    private final ProductService productService;

    @PostMapping(value = "/import")
    @ApiOperation(value = "Import products", response = ResponseEntity.class)
    public ResponseEntity<Map<String, Object>> importProducts(@RequestParam("file") final MultipartFile file) {
        return success(productService.importProducts(file));
    }

}