package com.hai.minh.ecommerce.ep.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.time.Instant;

import static com.hai.minh.ecommerce.ep.utils.StringUltils.TOKEN_TYPE_BEARER;

@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Data
public class EPToken implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("token_type")
    private String tokenType;
    @JsonProperty("identifier")
    private String identifier;
    @JsonProperty("expires")
    private Long expiresAt;
    @JsonProperty("expires_in")
    private Long expiresIn;
    @JsonProperty("access_token")
    private String accessToken;
    public EPToken(@JsonProperty("token_type") @NonNull String tokenType,
                      @JsonProperty("access_token") @NonNull String accessToken,
                      @JsonProperty("expires_in") @NonNull Long expiresIn) {
        this.tokenType = tokenType;
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
        this.expiresAt = Instant.now().getEpochSecond() - 1;
    }
    public static EPToken expiredToken() {
        return new EPToken(TOKEN_TYPE_BEARER, "expired", 0L);
    }
    public boolean isExpired() {
        log.info("NOW : {} ---- EXPIRED : {}", Instant.now().getEpochSecond(), expiresAt);
        return Instant.now().getEpochSecond() >  expiresAt;
    }
    public String value() {
        return this.accessToken;
    }
}