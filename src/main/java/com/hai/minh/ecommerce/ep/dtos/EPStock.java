package com.hai.minh.ecommerce.ep.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EPStock implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("level")
    private Integer level;
    @JsonProperty("availability")
    private String availability;
}