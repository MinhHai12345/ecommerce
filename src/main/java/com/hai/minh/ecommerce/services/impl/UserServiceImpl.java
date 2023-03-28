package com.hai.minh.ecommerce.services.impl;

import com.hai.minh.ecommerce.converter.RoleConverter;
import com.hai.minh.ecommerce.converter.UserConverter;
import com.hai.minh.ecommerce.dtos.UserDTO;
import com.hai.minh.ecommerce.entities.UserEntity;
import com.hai.minh.ecommerce.exceptions.InvalidArgumentException;
import com.hai.minh.ecommerce.repository.UserRepository;
import com.hai.minh.ecommerce.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private RoleConverter roleConverter;

    @Override
    @Transactional
    public UserDTO createOrUpdate(UserDTO user) {
        if (user == null) {
            throw new InvalidArgumentException("User not found!");
        }
        Integer id = user.getId();
        UserEntity entity = id == null ? this.create(user) : this.update(user, id);
        return userConverter.convertUserEntityToDTO(entity);
    }

    private UserEntity create(UserDTO user) {
        return userRepository.save(userConverter.convertUserDTOToEntity(user));
    }

    private UserEntity update(UserDTO user, Integer id) {
        UserEntity entity = userRepository.findById(id).orElse(null);
        if (entity != null) {
            entity.transfer(user, roleConverter.convertRoleDTOsToEntities(user.getRoles()));
        }
        return entity;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDTO findById(Integer id) {
        return userConverter.convertUserEntityToDTO(userRepository.findById(id).orElse(null));
    }
}
