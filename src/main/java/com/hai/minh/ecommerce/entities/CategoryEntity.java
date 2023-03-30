package com.hai.minh.ecommerce.entities;

import com.hai.minh.ecommerce.entities.commons.AbstractEntity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.Objects;

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

    @OneToMany(mappedBy = "category",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public List<SubCategoryEntity> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<SubCategoryEntity> subCategories) {
        this.subCategories = subCategories;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category", cascade = CascadeType.ALL)
    public List<ProductEntity> getProducts() {
        return products;
    }

    public void setProducts(List<ProductEntity> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryEntity that = (CategoryEntity) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
