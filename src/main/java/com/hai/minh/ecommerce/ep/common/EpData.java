package com.hai.minh.ecommerce.ep.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hai.minh.ecommerce.ep.dtos.pageInfo.Meta;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class EpData<T> implements Serializable {

    private static final long serialVersionUID = -7667221873686301566L;
    @JsonProperty("data")
    private T data;
    @JsonProperty("meta")
    private Meta meta;
}