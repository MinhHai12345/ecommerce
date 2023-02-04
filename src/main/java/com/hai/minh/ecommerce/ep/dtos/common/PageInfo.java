package com.hai.minh.ecommerce.ep.dtos.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class PageInfo implements Serializable {

    private static final long serialVersionUID = 1237844434431561530L;

    @JsonProperty("limit")
    private int limit;

    @JsonProperty("offset")
    private int offset;

    @JsonProperty("current")
    private int current;

    @JsonProperty("total")
    private int total;
}