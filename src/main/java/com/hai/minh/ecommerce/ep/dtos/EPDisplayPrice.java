package com.hai.minh.ecommerce.ep.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EPDisplayPrice implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("with_tax")
    private EPTax withTax;

    @JsonProperty("without_tax")
    private EPTax withoutTax;
}