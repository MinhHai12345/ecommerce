package com.hai.minh.ecommerce.exceptions.handler;

import com.hai.minh.ecommerce.dtos.errors.ErrorDTO;
import com.hai.minh.ecommerce.exceptions.CustomHttpServerErrorException;
import com.hai.minh.ecommerce.exceptions.CustomRestClientException;
import com.hai.minh.ecommerce.exceptions.InvalidArgumentException;
import com.hai.minh.ecommerce.exceptions.InvalidFileException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionTranslator {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionTranslator.class);

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidArgumentException.class)
    public ErrorDTO invalidArgumentException(InvalidArgumentException ex, HttpServletRequest request) {
        logger.error(ex.getMessage(), ex);
        return new ErrorDTO(ex.getCode(), ex.getMessage(), request.getRequestURI());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidFileException.class)
    public ErrorDTO invalidFileException(InvalidFileException ex, HttpServletRequest request) {
        logger.error(ex.getMessage(), ex);
        return new ErrorDTO(ex.getCode(), ex.getMessage(), request.getRequestURI());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    @ExceptionHandler(CustomRestClientException.class)
    public ErrorDTO restClientException(CustomRestClientException ex, HttpServletRequest request) {
        logger.error(ex.getMessage(), ex);
        return new ErrorDTO(ex.getCode(), ex.getMessage(), request.getRequestURI());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(CustomHttpServerErrorException.class)
    public ErrorDTO serverErrorException(CustomHttpServerErrorException ex, HttpServletRequest request) {
        logger.error(ex.getMessage(), ex);
        return new ErrorDTO(ex.getCode(), ex.getMessage(), request.getRequestURI());
    }

}
