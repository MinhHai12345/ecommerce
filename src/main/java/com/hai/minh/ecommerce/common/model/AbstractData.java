package com.hai.minh.ecommerce.common.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class AbstractData implements Serializable {
    @Serial
    private static final long serialVersionUID = -6058567028022955338L;

    private Long id;
    private boolean isDeleted;
}
