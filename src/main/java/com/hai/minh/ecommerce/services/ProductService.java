package com.hai.minh.ecommerce.services;

import org.springframework.web.multipart.MultipartFile;

public interface ProductService {

    void importProducts(MultipartFile file);
}
