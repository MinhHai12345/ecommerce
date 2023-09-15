package com.hai.minh.ecommerce.repository;

import com.hai.minh.ecommerce.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    @Query(value = "SELECT u FROM UserEntity u LEFT JOIN FETCH u.roles r WHERE u.username = :username ")
    Optional<UserEntity> findByUsername(@Param("username") String username);

    Optional<UserEntity> findByEmail(String email);
}
