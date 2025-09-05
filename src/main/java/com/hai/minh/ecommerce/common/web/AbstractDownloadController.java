package com.hai.minh.ecommerce.common.web;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public abstract class AbstractDownloadController {

    protected ResponseEntity<InputStreamResource> responseInputStreamResourceByByteArray(
            final byte[] byteArr, final String fileName) throws IOException {
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + fileName + "\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(byteArr.length)
                .body(new InputStreamResource(new ByteArrayInputStream(byteArr)));
    }
}
