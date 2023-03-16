package com.hai.minh.ecommerce.converter.impl;

import com.hai.minh.ecommerce.converter.RoleConverter;
import com.hai.minh.ecommerce.dtos.RoleDTO;
import com.hai.minh.ecommerce.entities.RoleEntity;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RoleConverterImpl implements RoleConverter {

    @Override
    public RoleDTO convertRoleEntityToDTO(final RoleEntity entity) {
        RoleDTO dto = null;
        if (entity != null) {
            dto = new RoleDTO();
            dto.setId(entity.getId());
            dto.setRole(entity.getRole());
            dto.setCreatedAt(entity.getCreatedAt());
            dto.setUpdatedAt(entity.getUpdatedAt());
        }
        return dto;
    }

    @Override
    public List<RoleDTO> convertRoleEntitiesToDTOs(final Collection<RoleEntity> entities) {
        return entities.stream().map(this::convertRoleEntityToDTO).collect(Collectors.toList());
    }

    @Override
    public RoleEntity convertRoleDTOToEntity(final RoleDTO dto) {
        RoleEntity entity = null;
        if (dto != null) {
            entity = new RoleEntity();
            entity.setId(dto.getId());
            entity.setRole(dto.getRole());
        }
        return entity;
    }

    @Override
    public List<RoleEntity> convertRoleDTOsToEntities(final Collection<RoleDTO> dtos) {
        return dtos.stream().map(this::convertRoleDTOToEntity).collect(Collectors.toList());
    }
}
