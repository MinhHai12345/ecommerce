package com.hai.minh.ecommerce.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hai.minh.ecommerce.dtos.commons.AbstractDTO;
import com.hai.minh.ecommerce.enums.ERole;

public class RoleDTO extends AbstractDTO {
    private static final long serialVersionUID = 7878675668758116455L;

    @JsonProperty("role")
    private ERole role;

    public ERole getRole() {
        return role;
    }

    public void setRole(ERole role) {
        this.role = role;
    }
}
