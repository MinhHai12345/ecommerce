package com.hai.minh.ecommerce.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hai.minh.ecommerce.commons.constants.Constants;
import com.hai.minh.ecommerce.dtos.errors.AuthenticateDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
           AuthenticationException authException) throws IOException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(response.getWriter(), new AuthenticateDTO(Constants.ERROR,
                HttpStatus.UNAUTHORIZED.getReasonPhrase(), request.getRequestURI()));
    }
}
