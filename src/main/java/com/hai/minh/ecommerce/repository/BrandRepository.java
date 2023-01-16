package com.hai.minh.ecommerce.repository;

import com.hai.minh.ecommerce.entities.BrandEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface BrandRepository extends CrudRepository<BrandEntity, Long> {
    List<BrandEntity> findByNameIn(Collection<String> name);
}
