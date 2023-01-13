package com.hai.minh.ecommerce.repository;

import com.hai.minh.ecommerce.entities.CategoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<CategoryEntity, Long> {
    CategoryEntity findByName(String name);
}
