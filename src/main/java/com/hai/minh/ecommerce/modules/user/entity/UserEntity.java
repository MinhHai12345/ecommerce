package com.hai.minh.ecommerce.modules.user.entity;

import com.hai.minh.ecommerce.common.entity.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

@Getter
@Setter
@Entity
@Table(name = "users")
public class UserEntity extends AbstractEntity {
    @Serial
    private static final long serialVersionUID = 6476444176592641915L;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String email;

//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "users_roles",
//            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
//    private Set<RoleEntity> roles = Sets.newHashSet();

}
