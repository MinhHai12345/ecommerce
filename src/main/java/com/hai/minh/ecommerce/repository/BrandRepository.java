package com.hai.minh.ecommerce.repository;

import com.hai.minh.ecommerce.entities.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<BrandEntity, Integer> {
    List<BrandEntity> findByNameIn(Collection<String> name);
}