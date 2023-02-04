package com.hai.minh.ecommerce.ep.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hai.minh.ecommerce.ep.dtos.common.DataCommon;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EPRelationshipCategories implements Serializable {

    private static final long serialVersionUID = -7624200960209981139L;

    @JsonProperty("data")
    private List<DataCommon> dataCategories;
}