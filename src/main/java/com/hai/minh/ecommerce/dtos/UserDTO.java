package com.hai.minh.ecommerce.dtos;

import com.hai.minh.ecommerce.dtos.commons.AbstractDTO;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

public class UserDTO extends AbstractDTO {
    private static final long serialVersionUID = 1851309541312800208L;

    @NotEmpty(message = " is not empty.")
    private String username;

    @Email(message = "format invalid!")
    @NotEmpty(message = " is not empty.")
    private String email;

    @Min(5)
    @NotEmpty(message = " is not empty and minimun 5 characters.")
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
