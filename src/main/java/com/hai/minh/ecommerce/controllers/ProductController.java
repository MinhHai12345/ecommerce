package com.hai.minh.ecommerce.controllers;

import com.hai.minh.ecommerce.controllers.commons.AbstractController;
import com.hai.minh.ecommerce.ep.service.EPAccessTokenService;
import com.hai.minh.ecommerce.services.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/api")
@Api(value = "API for Product")
public class ProductController extends AbstractController {
    private static final String URI = "/product";

    @Autowired
    private ProductService productService;
    @Autowired
    private EPAccessTokenService epAccessTokenService;

    @PostMapping(value = URI + "/import")
    @ApiOperation(value = "Import products", response = ResponseEntity.class)
    public ResponseEntity<Map<String, Object>> importProducts(@RequestParam("file") final MultipartFile file) {
        return success(productService.importProducts(file));
    }

    @PostMapping(value = URI + "/addProductToEP")
    @ApiOperation(value = "ADD PRODUCT TO EP", response = ResponseEntity.class)
    public ResponseEntity<Map<String, Object>> addProductToEP() {
        return success(epAccessTokenService.getToken());
    }
}