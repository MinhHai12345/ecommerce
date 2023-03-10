package com.hai.minh.ecommerce.ep.dtos.products;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hai.minh.ecommerce.ep.dtos.common.Identify;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EPRelationshipCategories implements Serializable {

    private static final long serialVersionUID = -7624200960209981139L;

    @JsonProperty("data")
    private List<Identify> dataCategories;
}