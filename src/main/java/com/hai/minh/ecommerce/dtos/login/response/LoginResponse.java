package com.hai.minh.ecommerce.dtos.login.response;

import com.hai.minh.ecommerce.dtos.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse implements Serializable {
    private static final long serialVersionUID = 6119312728040790591L;

    private String accessToken;
    private UserDTO user;

}
