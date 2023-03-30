package com.hai.minh.ecommerce.entities;

import com.hai.minh.ecommerce.entities.commons.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
public class ProductEntity extends AbstractEntity {

    private static final long serialVersionUID = 3426699509452566115L;
    private String sku;
    private String name;
    private String description;
    private BigDecimal price;
    private BigDecimal discount;
    private String currency;
    private String imageUrl;
    private CategoryEntity category;
    private Integer categoryId;
    private SubCategoryEntity subCategory;
    private Integer subCatgoryId;
    private BrandEntity brand;
    private Integer brandId;

    @Column
    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Lob
    @Column
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Column
    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    @Column
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Column
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sub_category_id")
    public SubCategoryEntity getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(SubCategoryEntity subCategory) {
        this.subCategory = subCategory;
    }

    @Column(name = "sub_category_id", insertable = false, updatable = false)
    public Integer getSubCatgoryId() {
        return subCatgoryId;
    }

    public void setSubCatgoryId(Integer subCatgoryId) {
        this.subCatgoryId = subCatgoryId;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    public BrandEntity getBrand() {
        return brand;
    }

    public void setBrand(BrandEntity brand) {
        this.brand = brand;
    }

    @Column(name = "brand_id", insertable = false, updatable = false)
    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

}
