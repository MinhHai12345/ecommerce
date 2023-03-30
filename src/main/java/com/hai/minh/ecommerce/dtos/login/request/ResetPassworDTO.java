package com.hai.minh.ecommerce.dtos.login.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
public class ResetPassworDTO implements Serializable {
    private static final long serialVersionUID = 1110210071606187423L;

    @JsonProperty("username")
    @NotEmpty(message = " is not empty.")
    private String username;

    @Min(5)
    @JsonProperty("password")
    @NotEmpty(message = " is not empty and minimun 5 characters.")
    private String password;

    @Min(5)
    @JsonProperty("repeat_password")
    @NotEmpty(message = " is not empty and minimun 5 characters.")
    private String repeatPassword;

}
