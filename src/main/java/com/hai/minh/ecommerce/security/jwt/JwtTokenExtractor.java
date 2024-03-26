package com.hai.minh.ecommerce.security.jwt;

import com.hai.minh.ecommerce.exceptions.InvalidArgumentException;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Component
public class JwtTokenExtractor {
    @Resource
    private JwtTokenUtil jwtTokenUtil;

    public String extract(HttpServletRequest request) {
        String bearerToken = request.getHeader(JwtConstant.AUTHORIZATION);
        if (bearerToken != null) {
            this.checkBearerToken(bearerToken);
        }
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(JwtConstant.BEARER)) {
            return bearerToken.substring(7, bearerToken.length());
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
        if (bearerToken.length() < JwtConstant.AUTHORIZATION.length()) {
            throw new InvalidArgumentException("Invalid authorization header size.");
        }
        if (!bearerToken.startsWith(JwtConstant.BEARER)) {
            throw new InvalidArgumentException("Jwt token invalid.");
        }
    }
}
