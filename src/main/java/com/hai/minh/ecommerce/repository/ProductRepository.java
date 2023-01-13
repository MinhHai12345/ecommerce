package com.hai.minh.ecommerce.repository;

import com.hai.minh.ecommerce.entities.ProductEntity;
import org.apache.commons.math3.stat.descriptive.summary.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, Long> {
}
