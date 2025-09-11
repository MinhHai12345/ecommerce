package com.hai.minh.ecommerce.security;

import com.hai.minh.ecommerce.exception.InvalidArgumentException;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@RequiredArgsConstructor
public class TokenExtractor {
    private static final String AUTHORIZATION = "Authorization";
    private static final String BEARER = "Bearer ";

    private final JwtTokenUtil jwtTokenUtil;

    public String extract(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION);
        if (bearerToken != null) {
            this.checkBearerToken(bearerToken);
        }
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER)) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public String extractUsername(String jwtToken) {
        try {
            return jwtTokenUtil.getUsernameFromToken(jwtToken);
        } catch (IllegalArgumentException e) {
            throw new InvalidArgumentException("Unable to get JWT Token.");
        } catch (ExpiredJwtException e) {
            throw new InvalidArgumentException("JWT Token has expired.");
        }
    }

    private void checkBearerToken(String bearerToken) {
        if (!StringUtils.hasText(bearerToken)) {
            throw new InvalidArgumentException("Authorization header must be not blank.");
        }
        if (bearerToken.length() < AUTHORIZATION.length()) {
            throw new InvalidArgumentException("Invalid authorization header size.");
        }
        if (!bearerToken.startsWith(BEARER)) {
            throw new InvalidArgumentException("Jwt token invalid.");
        }
    }
}
