package com.hai.minh.ecommerce.ep.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class EpData<T> implements Serializable {

    private static final long serialVersionUID = -7667221873686301566L;

    private T data;

}