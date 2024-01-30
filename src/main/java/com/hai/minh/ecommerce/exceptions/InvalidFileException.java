package com.hai.minh.ecommerce.exceptions;

import com.hai.minh.ecommerce.commons.constants.ErrorConstants;
import org.springframework.util.StringUtils;

public class InvalidFileException extends RuntimeException {
    private static final long serialVersionUID = 4348144930818199166L;
    private final String code;

    public InvalidFileException(final String message) {
        this(null, message);
    }

    public InvalidFileException(final String code, final String message) {
        super(message);
        this.code = StringUtils.hasText(code) ? code : ErrorConstants.ERR_INVALID_FILE;
    }

    public String getCode() {
        return code;
    }


}