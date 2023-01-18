package com.hai.minh.ecommerce.repository;

import com.hai.minh.ecommerce.entities.SubCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategoryEntity, Long> {
    List<SubCategoryEntity> findByNameIn(Collection<String> name);
}