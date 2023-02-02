package com.hai.minh.ecommerce.ep.dtos.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class EPResponseAccessToken implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("token_type")
    private String tokenType;
    @JsonProperty("identifier")
    private String identifier;
    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("expires")
    private Integer expires;
    @JsonProperty("expires_in")
    private Integer expiresIn;
}