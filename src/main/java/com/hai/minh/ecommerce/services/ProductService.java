package com.hai.minh.ecommerce.services;

import com.hai.minh.ecommerce.dtos.products.CSVProductDTO;
import com.hai.minh.ecommerce.entities.BrandEntity;
import com.hai.minh.ecommerce.entities.CategoryEntity;
import com.hai.minh.ecommerce.entities.ProductEntity;
import com.hai.minh.ecommerce.entities.SubCategoryEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface ProductService {

    void importProducts(MultipartFile file);

    void saveproductWithCSV(List<CSVProductDTO> csvProductDTOs, List<CategoryEntity> categories,
                            List<SubCategoryEntity> subCategories, List<BrandEntity> brands);

    boolean existProduct(CSVProductDTO filter, Map<String, ProductEntity> productEntityMap, Map<String, CategoryEntity> categoryEntityMap,
                         Map<String, SubCategoryEntity> subCategoryEntityMap, Map<String, BrandEntity> brandEntityMap);

}