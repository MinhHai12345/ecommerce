package com.hai.minh.ecommerce.dtos.errors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticateDTO implements Serializable {
    private static final long serialVersionUID = 3020481325513656887L;

    private String code;
    private String message;
    private String path;

}
