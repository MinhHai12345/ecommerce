package com.hai.minh.ecommerce.common.model.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Error implements Serializable {

    @Serial
    private static final long serialVersionUID = 9181132978617092177L;

    private String code;
    private String message;
}
