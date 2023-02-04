package com.hai.minh.ecommerce.ep.dtos.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class Meta implements Serializable {

    private static final long serialVersionUID = 2614293973089944697L;
    @JsonProperty("results")
    private Result results;

    @JsonProperty("page")
    private PageInfo page;
}