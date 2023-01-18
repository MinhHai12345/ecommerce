package com.hai.minh.ecommerce.entities;

import com.hai.minh.ecommerce.entities.commons.AbstractEntity;
import lombok.Builder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "brands")
public class BrandEntity extends AbstractEntity {
    private static final long serialVersionUID = 4543296606308418665L;

    private String name;
    private String brandUrl;

    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "brand_url")
    public String getBrandUrl() {
        return brandUrl;
    }

    public void setBrandUrl(String brandUrl) {
        this.brandUrl = brandUrl;
    }
}
