package com.hai.minh.ecommerce.modules.auth.model.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Setter
@Getter
public class LoginRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = 6548015539033901358L;

    @NotEmpty(message = " is not empty.")
    private String username;

    @Min(5)
    @NotEmpty(message = " is not empty and minimum 5 characters.")
    private String password;

}
