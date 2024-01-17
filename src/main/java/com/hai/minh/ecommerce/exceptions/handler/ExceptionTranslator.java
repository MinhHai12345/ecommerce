package com.hai.minh.ecommerce.exceptions.handler;

import com.hai.minh.ecommerce.dtos.errors.ErrorDTO;
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
    @ExceptionHandler({InvalidArgumentException.class, InvalidFileException.class})
    public ErrorDTO invalidException(InvalidArgumentException ex, HttpServletRequest request) {
        logger.error(ex.getMessage(), ex);
        return new ErrorDTO(ex.getCode(), ex.getMessage(), request.getRequestURI());
    }

}
