package com.hai.minh.ecommerce.modules.auth.service.impl;

import com.hai.minh.ecommerce.exception.InvalidArgumentException;
import com.hai.minh.ecommerce.modules.auth.model.request.LoginRequest;
import com.hai.minh.ecommerce.modules.auth.model.response.LoginResponse;
import com.hai.minh.ecommerce.security.JwtTokenUtil;
import com.hai.minh.ecommerce.modules.auth.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserDetailsService userDetailsService;

    @Override
    public LoginResponse login(final LoginRequest request) {
        this.authenticate(request.getUsername(), request.getPassword());
        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return new LoginResponse(token, null);
    }

    private void authenticate(final String username, final String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new InvalidArgumentException("USER_DISABLED", e.getMessage());
        } catch (BadCredentialsException e) {
            throw new InvalidArgumentException("INVALID_CREDENTIALS", e.getMessage());
        }
    }

}
