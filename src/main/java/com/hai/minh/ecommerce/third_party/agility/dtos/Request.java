package com.hai.minh.ecommerce.third_party.agility.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Request<T> implements Serializable {
    private static final long serialVersionUID = 9003653651055422991L;

    @JsonProperty("request")
    private T requestDTO;

    public T getRequestDTO() {
        return requestDTO;
    }

    public void setRequestDTO(T requestDTO) {
        this.requestDTO = requestDTO;
    }
}
