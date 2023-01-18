package com.hai.minh.ecommerce.repository;

import com.hai.minh.ecommerce.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    List<CategoryEntity> findByNameIn(Collection<String> name);

    CategoryEntity findByName(String name);
}