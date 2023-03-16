package com.hai.minh.ecommerce.dtos.login.request;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
public class LoginRequest implements Serializable {
    private static final long serialVersionUID = 6548015539033901358L;

    @NotEmpty(message = " is not empty.")
    private String username;

    @Min(5)
    @NotEmpty(message = " is not empty and minimun 5 characters.")
    private String password;

}
