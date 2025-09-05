package com.hai.minh.ecommerce.exception;

import com.hai.minh.ecommerce.constant.Constants;
import lombok.Getter;
import org.springframework.util.StringUtils;

import java.io.Serial;

@Getter
public class InvalidArgumentException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 3912915980738627441L;

    private final String code;

    public InvalidArgumentException(final String message) {
        this(null, message);
    }

    public InvalidArgumentException(final String code, final String message) {
        super(message);
        this.code = StringUtils.hasText(code) ? code : Constants.ERR_INVALID_ARGUMENT;
    }

}
