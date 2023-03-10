package com.hai.minh.ecommerce.ep.dtos.products;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EPProductDto extends EPProductBaseDto implements Serializable {

    private static final long serialVersionUID = -2521187455351016082L;

    @JsonProperty("type")
    private String type;

    @JsonProperty("id")
    private String id;

    @JsonProperty("slug")
    private String slug;

    @JsonProperty("sku")
    private String sku;

    @JsonProperty("manage_stock")
    private boolean manageStock;

    @JsonProperty("price")
    private List<EPProductPrice> price;

    @JsonProperty("commodity_type")
    private String commodityType;

    @JsonProperty("meta")
    private EPProductMeta meta;

    @JsonProperty("weight")
    private EPProductWeight weight;

    @JsonProperty("relationships")
    private EPProductRelationship relationships;

    @JsonProperty("product_variation_id")
    private String productVariationId;

    @JsonProperty("status")
    private String status;

    @JsonProperty("description")
    private String description;
}