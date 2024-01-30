package com.hai.minh.ecommerce.exceptions;

import com.hai.minh.ecommerce.commons.constants.ErrorConstants;
import org.springframework.util.StringUtils;

public class InvalidArgumentException extends RuntimeException {
    private static final long serialVersionUID = 3912915980738627441L;

    private final String code;

    public InvalidArgumentException(final String message) {
        this(null, message);
    }

    public InvalidArgumentException(final String code, final String message) {
        super(message);
        this.code = StringUtils.hasText(code) ? code : ErrorConstants.ERR_INVALID_ARGUMENT;
    }

    public String getCode() {
        return code;
    }

}
