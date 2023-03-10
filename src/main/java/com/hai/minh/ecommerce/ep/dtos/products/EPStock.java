package com.hai.minh.ecommerce.ep.dtos.products;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EPStock implements Serializable {

    private static final long serialVersionUID = -3143116418270200573L;

    @JsonProperty("level")
    private Integer level;

    @JsonProperty("availability")
    private String availability;
}