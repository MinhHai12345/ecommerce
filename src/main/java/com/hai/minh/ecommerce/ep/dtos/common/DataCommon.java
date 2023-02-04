package com.hai.minh.ecommerce.ep.dtos.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DataCommon implements Serializable {

    private static final long serialVersionUID = 5569969625889613055L;

    @JsonProperty("id")
    private String id;

    @JsonProperty("type")
    private String type;
}