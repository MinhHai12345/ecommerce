package com.hai.minh.ecommerce.services;

import org.springframework.web.multipart.MultipartFile;

public interface ProductService {

    boolean importProducts(MultipartFile file);
}