package com.hai.minh.ecommerce.services.impl;

import com.hai.minh.ecommerce.dtos.products.CSVProductDTO;
import com.hai.minh.ecommerce.services.ProductService;
import com.hai.minh.ecommerce.utils.CSVUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Override
    public void importProducts(MultipartFile file) {
        List<CSVProductDTO> productDTOS = CSVUtils.csvToDTO(file, CSVProductDTO.class);
        if (CollectionUtils.isNotEmpty(productDTOS)) {
            System.out.println(productDTOS);
        }

    }
}
