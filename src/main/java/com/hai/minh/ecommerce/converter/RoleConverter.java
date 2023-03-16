package com.hai.minh.ecommerce.converter;

import com.hai.minh.ecommerce.dtos.RoleDTO;
import com.hai.minh.ecommerce.entities.RoleEntity;

import java.util.Collection;
import java.util.List;

public interface RoleConverter {

    RoleDTO convertRoleEntityToDTO(RoleEntity entity);

    List<RoleDTO> convertRoleEntitiesToDTOs(Collection<RoleEntity> entities);

    RoleEntity convertRoleDTOToEntity(RoleDTO dto);

    List<RoleEntity> convertRoleDTOsToEntities(Collection<RoleDTO> dtos);
}
