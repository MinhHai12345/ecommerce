package com.hai.minh.ecommerce.third_party.agility.dtos.authen;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO implements Serializable {
    private static final long serialVersionUID = -3750267301011198408L;

    @JsonProperty("LoginID")
    private String loginID;

    @JsonProperty("Password")
    private String password;
}
