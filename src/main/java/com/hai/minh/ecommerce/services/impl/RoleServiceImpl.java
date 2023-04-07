package com.hai.minh.ecommerce.services.impl;

import com.hai.minh.ecommerce.converter.RoleConverter;
import com.hai.minh.ecommerce.dtos.RoleDTO;
import com.hai.minh.ecommerce.entities.RoleEntity;
import com.hai.minh.ecommerce.exceptions.InvalidArgumentException;
import com.hai.minh.ecommerce.repository.RoleRepository;
import com.hai.minh.ecommerce.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleConverter roleConverter;

    @Override
    public RoleDTO createOrUpdate(RoleDTO role) {
        if (role == null) {
            throw new InvalidArgumentException("Role not found!");
        }
        Integer id = role.getId();
        RoleEntity entity = id == null ? this.create(role) : this.update(role, id);
        return roleConverter.convertRoleEntityToDTO(entity);
    }

    private RoleEntity create(RoleDTO role) {
        RoleEntity entity = roleRepository.findByRole(role.getRole()).orElse(null);
        if (entity != null) {
            throw new InvalidArgumentException("Role has been exist!");
        }
        return roleRepository.save(roleConverter.convertRoleDTOToEntity(role));
    }

    private RoleEntity update(RoleDTO role, Integer id) {
        RoleEntity entity = roleRepository.findById(id).orElse(null);
        if (entity != null) {
            entity.setRole(role.getRole());
        }
        return entity;
    }
}
