package com.hai.minh.ecommerce.services.impl;

import com.hai.minh.ecommerce.converter.UserConverter;
import com.hai.minh.ecommerce.dtos.login.request.LoginRequest;
import com.hai.minh.ecommerce.dtos.login.response.LoginResponse;
import com.hai.minh.ecommerce.entities.UserEntity;
import com.hai.minh.ecommerce.exceptions.InvalidArgumentException;
import com.hai.minh.ecommerce.security.jwt.JwtTokenUtil;
import com.hai.minh.ecommerce.repository.UserRepository;
import com.hai.minh.ecommerce.services.AuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private UserRepository userRepository;

    @Override
    public LoginResponse login(final LoginRequest request) {
        LoginResponse loginResponse = null;
        try {
            this.authenticate(request.getUsername(), request.getPassword());
            final UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(request.getUsername());
            final String token = jwtTokenUtil.generateToken(userDetails);
            final UserEntity userEntity = userRepository.findByUsername(request.getUsername()).orElse(null);
            loginResponse = new LoginResponse(token, userConverter.convertUserEntityToDTO(userEntity));
        } catch (Exception e) {
            logger.error("LOGIN FAIL:: ".concat(e.getMessage()));
        }
        return loginResponse;
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
