package com.hai.minh.ecommerce.repository;

import com.hai.minh.ecommerce.entities.CategoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface CategoryRepository extends CrudRepository<CategoryEntity, Long> {
    List<CategoryEntity> findByNameIn(Collection<String> name);
}