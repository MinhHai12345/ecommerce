package com.hai.minh.ecommerce.services;

import com.hai.minh.ecommerce.dtos.UserDTO;

public interface UserService {

    UserDTO createOrUpdate(UserDTO user);

    UserDTO findById(Integer id);


}
