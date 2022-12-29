package com.hai.minh.ecommerce.dtos.products;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
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

}
