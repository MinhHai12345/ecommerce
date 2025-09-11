package com.hai.minh.ecommerce.modules.product.entity;

import com.hai.minh.ecommerce.common.entity.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "products")
public class Product extends AbstractEntity {

    @Column
    private String sku;

    @Column
    private String name;

    @Lob
    @Column
    private String description;

    @Column
    private BigDecimal price;

    @Column
    private Integer stockQuantity;

    @Column
    private String imageUrl;

    @Column
    private boolean active;

}
