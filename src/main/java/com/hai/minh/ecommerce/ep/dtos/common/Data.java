package com.hai.minh.ecommerce.ep.dtos.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@lombok.Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Data implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("id")
    private String id;
    @JsonProperty("type")
    private String type;
}