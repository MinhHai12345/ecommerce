package com.hai.minh.ecommerce.exceptions;

import com.hai.minh.ecommerce.commons.constants.ErrorConstants;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestClientException;

public class CustomRestClientException extends RestClientException {
    private static final long serialVersionUID = 4348144930818199166L;
    private final String code;

    public CustomRestClientException(final String message) {
        this(null, message);
    }

    public CustomRestClientException(final String code, final String message) {
        super(message);
        this.code = StringUtils.hasText(code) ? code : ErrorConstants.ERR_CALL_REST_CLIENT_ERROR;
    }

    public String getCode() {
        return code;
    }

}
