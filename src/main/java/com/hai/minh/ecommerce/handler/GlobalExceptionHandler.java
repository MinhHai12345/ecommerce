package com.hai.minh.ecommerce.handler;

import com.hai.minh.ecommerce.exception.CustomHttpServerErrorException;
import com.hai.minh.ecommerce.exception.CustomRestClientException;
import com.hai.minh.ecommerce.exception.InvalidArgumentException;
import com.hai.minh.ecommerce.exception.InvalidFileException;
import com.hai.minh.ecommerce.common.model.response.Error;
import com.hai.minh.ecommerce.common.model.response.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidArgumentException.class)
    public ResponseEntity<Object> handleInvalidArgumentException(InvalidArgumentException ex) {
        return ResponseEntity.badRequest()
                .body(Error.builder()
                        .code(ex.getCode())
                        .message(ex.getMessage())
                        .build());
    }


    @ExceptionHandler(InvalidFileException.class)
    public ResponseEntity<Object> invalidFileException(InvalidFileException ex) {
        Error error = new Error(ex.getCode(), ex.getMessage());
        return ResponseEntity.badRequest()
                .body(Response.builder()
                        .errors(Collections.singletonList(error))
                        .build());
    }

    @ExceptionHandler({CustomRestClientException.class})
    public ResponseEntity<Object> restClientException(CustomRestClientException ex) {
        Error error = new Error(ex.getCode(), ex.getMessage());
        return ResponseEntity.badRequest()
                .body(Response.builder()
                        .errors(Collections.singletonList(error))
                        .build());
    }

    @ExceptionHandler(CustomHttpServerErrorException.class)
    public ResponseEntity<Object> serverErrorException(CustomHttpServerErrorException ex) {
        Error error = new Error(ex.getCode(), ex.getMessage());
        return ResponseEntity.internalServerError()
                .body(Response.builder()
                        .errors(Collections.singletonList(error))
                        .build());
    }

}
