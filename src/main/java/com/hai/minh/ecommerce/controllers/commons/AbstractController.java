package com.hai.minh.ecommerce.controllers.commons;

import com.hai.minh.ecommerce.commons.constants.Constants;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class AbstractController {

    protected ResponseEntity<Map<String, Object>> success(final Object data) {
        return this.success(data, null, null, true);
    }

    protected ResponseEntity<Map<String, Object>> success(final Object data, final String code,
           final String message, final boolean needTranslate) {
        final Map<String, Object> successRes = new LinkedHashMap<>();
        successRes.put(Constants.DATA, data);
        String codeRes = StringUtils.hasText(code) ? code : HttpStatus.OK.name();
        successRes.put(Constants.CODE, codeRes);
//        if (StringUtils.hasText(message)) {
//            final String translated = needTranslate ? CommonResourceBundle.getMessage(message)
//                    : message;
//            successRes.put(Constants.MESSAGE, translated);
//        } else {
//            successRes.put(Constants.MESSAGE, HttpStatus.OK.name());
//        }
        return new ResponseEntity<>(successRes, HttpStatus.OK);
    }

    protected ResponseEntity<FileSystemResource> responseFileSystemResourceByByteArray(
            final byte[] byteArr, final String fileName) throws IOException {
        final File file = File.createTempFile(fileName, null);
        FileUtils.writeByteArrayToFile(file, byteArr);
        final FileSystemResource fileSystemResource = new FileSystemResource(file);
        file.deleteOnExit();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + fileName + "\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM).contentLength(file.length())
                .body(fileSystemResource);
    }
}
