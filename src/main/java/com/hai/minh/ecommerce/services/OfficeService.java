package com.hai.minh.ecommerce.services;

import org.springframework.web.multipart.MultipartFile;

public interface OfficeService {

    byte[] pdfToExcel(MultipartFile file, boolean isMerged);

    String buildExcelNameFromPDFFile(String inputName);

    String imageToText(MultipartFile file);
}
