package com.hai.minh.ecommerce.services;

import com.hai.minh.ecommerce.dtos.UserDTO;
import com.hai.minh.ecommerce.dtos.login.request.ResetPasswordDTO;
import com.hai.minh.ecommerce.entities.UserEntity;

import java.util.Optional;

public interface UserService {

    UserDTO createOrUpdate(UserDTO user);

    UserDTO findById(Integer id);

    Optional<UserEntity> findByUsername(String username);

    Optional<UserEntity> findByEmail(String email);

    boolean resetPassword(ResetPasswordDTO request);


}
