package com.hai.minh.ecommerce.exception;

import com.hai.minh.ecommerce.constant.Constants;
import lombok.Getter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpServerErrorException;

import java.io.Serial;
import java.nio.charset.Charset;

@Getter
public class CustomHttpServerErrorException extends HttpServerErrorException {
    @Serial
    private static final long serialVersionUID = 4532233333872432102L;
    private final String code;
    private final String message;

    public CustomHttpServerErrorException(HttpStatus statusCode, String statusText, HttpHeaders headers, byte[] body, Charset charset, String message) {
        super(statusCode, statusText, headers, body, charset);
        this.message = message;
        this.code = Constants.ERR_INTERNAL_SERVER_ERROR;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
