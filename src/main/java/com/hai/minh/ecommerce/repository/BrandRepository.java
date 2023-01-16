package com.hai.minh.ecommerce.repository;

import com.hai.minh.ecommerce.entities.BrandEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends CrudRepository<BrandEntity, Long> {
    BrandEntity findByName(String name);
}
