package com.hai.minh.ecommerce.repository;

import com.hai.minh.ecommerce.entities.RoleEntity;
import com.hai.minh.ecommerce.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {

    Optional<RoleEntity> findByRole(ERole role);
}
