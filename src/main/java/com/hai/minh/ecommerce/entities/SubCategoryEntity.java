package com.hai.minh.ecommerce.entities;

import com.hai.minh.ecommerce.entities.commons.AbstractEntity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "sub_categories")
public class SubCategoryEntity extends AbstractEntity {
    private static final long serialVersionUID = -8852925049834792168L;

    private String name;
    private CategoryEntity category;
    private Integer categoryId;
    private List<ProductEntity> products;

    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    @Column(name = "category_id", insertable = false, updatable = false)
    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "subCategory", cascade = CascadeType.ALL)
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
        SubCategoryEntity that = (SubCategoryEntity) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
