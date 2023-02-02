package com.hai.minh.ecommerce.ep.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EPProductPrice implements Serializable {
    private static final long serialVersionUID = 1L;
    @JsonProperty("amount")
    private Double amount;
    @JsonProperty("currency")
    private String currency;
    @JsonProperty("includes_tax")
    private boolean includesTax;
}