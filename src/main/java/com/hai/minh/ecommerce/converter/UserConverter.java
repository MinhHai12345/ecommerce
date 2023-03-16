package com.hai.minh.ecommerce.converter;

import com.hai.minh.ecommerce.dtos.UserDTO;
import com.hai.minh.ecommerce.entities.UserEntity;

import java.util.Collection;
import java.util.List;

public interface UserConverter {

    UserDTO convertUserEntityToDTO(UserEntity entity);

    List<UserDTO> convertUserEntitiesToDTOs(Collection<UserEntity> entities);

    UserEntity convertUserDTOToEntity(UserDTO dto);

    List<UserEntity> convertUserDTOsToEntities(Collection<UserDTO> dtos);

}
