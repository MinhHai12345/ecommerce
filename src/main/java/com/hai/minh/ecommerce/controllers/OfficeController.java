package com.hai.minh.ecommerce.controllers;


import com.hai.minh.ecommerce.controllers.commons.AbstractController;
import com.hai.minh.ecommerce.services.OfficeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api")
@Api(value = "API for Office")
public class OfficeController extends AbstractController {
    private static final String URI = "/office/convert";

    @Autowired
    private OfficeService officeService;

    @PostMapping(value = URI + "/pdf-to-excel")
    @ApiOperation(value = "Convert pdf to excel", response = ResponseEntity.class)
    public ResponseEntity<FileSystemResource> pdfToExcel(@RequestParam("file") final MultipartFile file,
           @RequestParam(value = "isMerged", required = false, defaultValue = "false") final boolean isMerged) throws IOException {
        return responseFileSystemResourceByByteArray(officeService.pdfToExcel(file, isMerged),
                officeService.buildExcelNameFromPDFFile(file.getOriginalFilename()));
    }

    @PostMapping(value = URI + "/image-to-text")
    @ApiOperation(value = "Convert image to text", response = ResponseEntity.class)
    public ResponseEntity<Map<String, Object>> imageToText() {
        return this.success(officeService.crackImage());
    }
}
