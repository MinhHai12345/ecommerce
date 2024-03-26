package com.hai.minh.ecommerce.dtos.login.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class ResetPasswordDTO implements Serializable {
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }
}
