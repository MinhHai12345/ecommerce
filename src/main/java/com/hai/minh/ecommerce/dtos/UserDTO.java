package com.hai.minh.ecommerce.dtos;

import com.hai.minh.ecommerce.dtos.commons.AbstractDTO;

import java.util.Set;

public class UserDTO extends AbstractDTO {
    private static final long serialVersionUID = 1851309541312800208L;

    private String username;
    private String password;
    private Set<RoleDTO> roles;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<RoleDTO> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleDTO> roles) {
        this.roles = roles;
    }
}
