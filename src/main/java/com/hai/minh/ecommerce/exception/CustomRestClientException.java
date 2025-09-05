package com.hai.minh.ecommerce.exception;

import com.hai.minh.ecommerce.constant.Constants;
import lombok.Getter;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestClientException;

import java.io.Serial;

@Getter
public class CustomRestClientException extends RestClientException {
    @Serial
    private static final long serialVersionUID = 4348144930818199166L;
    private final String code;

    public CustomRestClientException(final String message) {
        this(null, message);
    }

    public CustomRestClientException(final String code, final String message) {
        super(message);
        this.code = StringUtils.hasText(code) ? code : Constants.ERR_CALL_REST_CLIENT_ERROR;
    }

}
