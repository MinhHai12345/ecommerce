package com.hai.minh.ecommerce.services;

import com.hai.minh.ecommerce.dtos.products.CSVProductDTO;
import com.hai.minh.ecommerce.entities.BrandEntity;
import com.hai.minh.ecommerce.entities.CategoryEntity;
import com.hai.minh.ecommerce.entities.ProductEntity;
import com.hai.minh.ecommerce.entities.SubCategoryEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface ProductService {

    boolean importProducts(MultipartFile file);

    void saveProductWithCSV(List<CSVProductDTO> csvProductDTOs, List<CategoryEntity> categories,
                            List<SubCategoryEntity> subCategories, List<BrandEntity> brands);

    boolean existProduct(CSVProductDTO filter, Map<String, ProductEntity> productEntityMap);

    ResponseEntity<Map<String, Object>> createProductToEP();
}