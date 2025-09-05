package com.hai.minh.ecommerce.modules.auth.service.impl;

import com.hai.minh.ecommerce.modules.user.repository.UserRepository;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
//                        .authorities(it.getRoles().stream()
//                                .map(role -> new SimpleGrantedAuthority(role.getRole().name()))
//                                .collect(Collectors.toList()))
                                .build()
        ).orElseThrow(() -> new EntityExistsException("User " + username + " not found!"));
    }
}
