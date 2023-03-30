package com.hai.minh.ecommerce.entities;

import com.google.common.collect.Sets;
import com.hai.minh.ecommerce.dtos.UserDTO;
import com.hai.minh.ecommerce.entities.commons.AbstractEntity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserEntity extends AbstractEntity {
    private static final long serialVersionUID = 6476444176592641915L;

    private String username;
    private String password;
    private String email;
    private Set<RoleEntity> roles = Sets.newHashSet();

    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    public Set<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
    }


    public void transfer(UserDTO dto, Set<RoleEntity> roles) {
        if (dto != null) {
            this.setId(dto.getId());
            this.setEmail(dto.getEmail());
            this.setUsername(dto.getUsername());
            this.setRoles(roles);
            this.setDeleted(dto.isDeleted());
        }
    }
}
