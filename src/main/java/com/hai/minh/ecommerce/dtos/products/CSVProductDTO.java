package com.hai.minh.ecommerce.dtos.products;

import com.opencsv.bean.CsvBindByName;

import java.io.Serializable;
import java.math.BigDecimal;

public class CSVProductDTO implements Serializable {
    private static final long serialVersionUID = 5220973148926670196L;

    @CsvBindByName(column = "category")
    private String category;

    @CsvBindByName(column = "subcategory")
    private String subCategory;

    @CsvBindByName(column = "name")
    private String name;

    @CsvBindByName(column = "current_price")
    private BigDecimal currentPrice;

    @CsvBindByName(column = "raw_price")
    private BigDecimal rawPrice;

    @CsvBindByName(column = "currency")
    private String currency;

    @CsvBindByName(column = "discount")
    private BigDecimal discount;

    @CsvBindByName(column = "likes_count")
    private Integer likesCount;

    @CsvBindByName(column = "is_new")
    private boolean isNew;

    @CsvBindByName(column = "brand")
    private String brand;

    @CsvBindByName(column = "brand_url")
    private String brandUrl;

    @CsvBindByName(column = "codCountry")
    private String codeCountry;

    @CsvBindByName(column = "variation_0_color")
    private String variation0Color;

    @CsvBindByName(column = "variation_1_color")
    private String variation1Color;

    @CsvBindByName(column = "variation_0_thumbnail")
    private String variation0Thumbnail;

    @CsvBindByName(column = "variation_0_image")
    private String variation0Image;

    @CsvBindByName(column = "variation_1_thumbnail")
    private String variation1Thumbnail;

    @CsvBindByName(column = "variation_1_image")
    private String variation1Image;

    @CsvBindByName(column = "image_url")
    private String imageUrl;

    @CsvBindByName(column = "url")
    private String url;

    @CsvBindByName(column = "id")
    private String id;

    @CsvBindByName(column = "model")
    private String model;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }

    public BigDecimal getRawPrice() {
        return rawPrice;
    }

    public void setRawPrice(BigDecimal rawPrice) {
        this.rawPrice = rawPrice;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public Integer getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(Integer likesCount) {
        this.likesCount = likesCount;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getBrandUrl() {
        return brandUrl;
    }

    public void setBrandUrl(String brandUrl) {
        this.brandUrl = brandUrl;
    }

    public String getCodeCountry() {
        return codeCountry;
    }

    public void setCodeCountry(String codeCountry) {
        this.codeCountry = codeCountry;
    }

    public String getVariation0Color() {
        return variation0Color;
    }

    public void setVariation0Color(String variation0Color) {
        this.variation0Color = variation0Color;
    }

    public String getVariation1Color() {
        return variation1Color;
    }

    public void setVariation1Color(String variation1Color) {
        this.variation1Color = variation1Color;
    }

    public String getVariation0Thumbnail() {
        return variation0Thumbnail;
    }

    public void setVariation0Thumbnail(String variation0Thumbnail) {
        this.variation0Thumbnail = variation0Thumbnail;
    }

    public String getVariation0Image() {
        return variation0Image;
    }

    public void setVariation0Image(String variation0Image) {
        this.variation0Image = variation0Image;
    }

    public String getVariation1Thumbnail() {
        return variation1Thumbnail;
    }

    public void setVariation1Thumbnail(String variation1Thumbnail) {
        this.variation1Thumbnail = variation1Thumbnail;
    }

    public String getVariation1Image() {
        return variation1Image;
    }

    public void setVariation1Image(String variation1Image) {
        this.variation1Image = variation1Image;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
