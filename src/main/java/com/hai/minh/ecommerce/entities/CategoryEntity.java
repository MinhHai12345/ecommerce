package com.hai.minh.ecommerce.entities;

import com.hai.minh.ecommerce.entities.commons.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "categories")
public class CategoryEntity extends AbstractEntity {
    private static final long serialVersionUID = 7195340411380794077L;

    private String name;
    private List<SubCategoryEntity> subCategories;
    private List<ProductEntity> products;

    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "category",fetch = FetchType.LAZY)
    public List<SubCategoryEntity> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<SubCategoryEntity> subCategories) {
        this.subCategories = subCategories;
    }

    @OneToMany(fetch = FetchType.LAZY)
    public List<ProductEntity> getProducts() {
        return products;
    }

    public void setProducts(List<ProductEntity> products) {
        this.products = products;
    }
}
