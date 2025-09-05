package com.hai.minh.ecommerce.exception;

import com.hai.minh.ecommerce.constant.Constants;
import lombok.Getter;
import org.springframework.util.StringUtils;

import java.io.Serial;

@Getter
public class InvalidFileException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 4348144930818199166L;
    private final String code;

    public InvalidFileException(final String message) {
        this(null, message);
    }

    public InvalidFileException(final String code, final String message) {
        super(message);
        this.code = StringUtils.hasText(code) ? code : Constants.ERR_INVALID_FILE;
    }


}
