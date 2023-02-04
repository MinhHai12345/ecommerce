package com.hai.minh.ecommerce.ep.dtos.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Meta {
    private static final long serialVersionUID = 1L;

    @JsonProperty("results")
    private Result results;

    @JsonProperty("page")
    private PageInfo page;
}