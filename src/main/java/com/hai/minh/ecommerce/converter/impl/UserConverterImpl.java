package com.hai.minh.ecommerce.converter.impl;

import com.google.common.collect.Sets;
import com.hai.minh.ecommerce.converter.RoleConverter;
import com.hai.minh.ecommerce.converter.UserConverter;
import com.hai.minh.ecommerce.dtos.UserDTO;
import com.hai.minh.ecommerce.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserConverterImpl implements UserConverter {

    @Autowired
    private RoleConverter roleConverter;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDTO convertUserEntityToDTO(final UserEntity entity) {
        UserDTO dto = null;
        if (entity != null) {
            dto = new UserDTO();
            dto.setId(entity.getId());
            dto.setUsername(entity.getUsername());
            dto.setPassword(entity.getPassword());
            dto.setRoles(Sets.newHashSet(roleConverter.convertRoleEntitiesToDTOs(entity.getRoles())));
            dto.setCreatedAt(entity.getCreatedAt());
            dto.setUpdatedAt(entity.getUpdatedAt());
        }
        return dto;
    }

    @Override
    public List<UserDTO> convertUserEntitiesToDTOs(final Collection<UserEntity> entities) {
        return entities.stream().map(this::convertUserEntityToDTO).collect(Collectors.toList());
    }

    @Override
    public UserEntity convertUserDTOToEntity(final UserDTO dto) {
        UserEntity entity = null;
        if (dto != null) {
            entity = new UserEntity();
            entity.setId(dto.getId());
            entity.setUsername(dto.getUsername());
            entity.setPassword(passwordEncoder.encode(dto.getPassword()));
            entity.setRoles(Sets.newHashSet(roleConverter.convertRoleDTOsToEntities(dto.getRoles())));
        }
        return entity;
    }

    @Override
    public List<UserEntity> convertUserDTOsToEntities(final Collection<UserDTO> dtos) {
        return dtos.stream().map(this::convertUserDTOToEntity).collect(Collectors.toList());
    }
}
