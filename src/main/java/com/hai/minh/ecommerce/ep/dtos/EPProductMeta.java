package com.hai.minh.ecommerce.ep.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hai.minh.ecommerce.ep.dtos.common.TimeStamps;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EPProductMeta implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("timestamps")
    private TimeStamps timeStamps;

    @JsonProperty("display_price")
    private EPDisplayPrice displayPrice;

    @JsonProperty("stock")
    private EPStock stock;
}