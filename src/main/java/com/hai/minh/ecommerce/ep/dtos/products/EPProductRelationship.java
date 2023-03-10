package com.hai.minh.ecommerce.ep.dtos.products;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EPProductRelationship implements Serializable {

    private static final long serialVersionUID = -3647180810370364135L;

    @JsonProperty("categories")
    private EPRelationshipCategories categories;

    @JsonProperty("brands")
    private EPRelationshipBrands brands;
}