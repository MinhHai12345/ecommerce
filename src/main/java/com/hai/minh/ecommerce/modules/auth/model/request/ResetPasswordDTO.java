package com.hai.minh.ecommerce.modules.auth.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Setter
@Getter
public class ResetPasswordDTO implements Serializable {
    @Serial
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
