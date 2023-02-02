package com.hai.minh.ecommerce.ep.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EPProductRelationship implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("categories")
    private EPRelationshipCategories categories;

    @JsonProperty("brands")
    private EPRelationshipBrands brands;
}