package com.hai.minh.ecommerce.ep.dtos.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class EPData<T> implements Serializable {

    private static final long serialVersionUID = -6444319184862460309L;

    @JsonProperty("data")
    private T data;

    @JsonProperty("meta")
    private Meta meta;
}