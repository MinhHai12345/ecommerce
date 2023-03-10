package com.hai.minh.ecommerce.ep.dtos.auth.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class EPRequestAccessToken implements Serializable {

    private static final long serialVersionUID = -8265367940268511095L;

    @JsonProperty("client_id")
    private String clientId;

    @JsonProperty("client_secret")
    private String clientSecret;

    @JsonProperty("grant_type")
    private String grantType;
}