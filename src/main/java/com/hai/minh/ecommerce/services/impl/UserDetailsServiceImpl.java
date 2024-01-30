package com.hai.minh.ecommerce.services.impl;

import com.hai.minh.ecommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).map(it ->
                User.builder()
                        .username(it.getUsername())
                        .password(it.getPassword())
                        .authorities(it.getRoles().stream()
                                .map(role -> new SimpleGrantedAuthority(role.getRole().name()))
                                .collect(Collectors.toList())).build()
        ).orElseThrow(() -> new EntityExistsException("User " + username + " not found!"));
    }
}
