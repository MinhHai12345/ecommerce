package com.hai.minh.ecommerce.entities;

import com.hai.minh.ecommerce.entities.commons.AbstractEntity;
import com.hai.minh.ecommerce.enums.ERole;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class RoleEntity extends AbstractEntity {
    private static final long serialVersionUID = 7975629404079291184L;

    private ERole role;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    public ERole getRole() {
        return role;
    }

    public void setRole(ERole role) {
        this.role = role;
    }

}
