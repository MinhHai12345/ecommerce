package com.hai.minh.ecommerce.ep.dtos.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TimeStamps implements Serializable {

    private static final long serialVersionUID = 8540203894194925081L;

    @JsonProperty("created_at")
    private String createAt;

    @JsonProperty("updated_at")
    private String updatedAt;
}