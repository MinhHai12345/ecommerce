package com.hai.minh.ecommerce.modules.auth.model.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Setter
@Getter
public class LoginResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = 6119312728040790591L;

    private String accessToken;
    private UserData user;

    public LoginResponse() {
    }

    public LoginResponse(String accessToken, UserData user) {
        this.accessToken = accessToken;
        this.user = user;
    }

}
