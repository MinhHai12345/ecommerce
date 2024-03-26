package com.hai.minh.ecommerce.dtos.login.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class LoginRequest implements Serializable {
    private static final long serialVersionUID = 6548015539033901358L;

    @NotEmpty(message = " is not empty.")
    private String username;

    @Min(5)
    @NotEmpty(message = " is not empty and minimum 5 characters.")
    private String password;

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
}
