package com.hai.minh.ecommerce.dtos.errors;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hai.minh.ecommerce.commons.constants.Constants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDTO implements Serializable {
    private static final long serialVersionUID = 3020481325513656887L;

    private String code;
    private String message;
    private String path;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.DATE_FORMAT_DDMMYYYYHHMMSS_HYPHEN, timezone = Constants.GLOBAL_TIMEZONE)
    private Timestamp timestamp;

    public ErrorDTO(String code, String message, String path) {
        this.code = code;
        this.message = message;
        this.path = path;
    }
}
