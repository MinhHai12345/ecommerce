package com.hai.minh.ecommerce.services;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface ProductService {

    boolean importProducts(MultipartFile file);

    ResponseEntity<Map<String, Object>> createProductToEP();
}