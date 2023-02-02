package com.hai.minh.ecommerce.ep.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hai.minh.ecommerce.ep.dtos.common.Data;

import java.io.Serializable;
import java.util.List;

@lombok.Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EPRelationshipCategories implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("data")
    private List<Data> dataCategories;
}