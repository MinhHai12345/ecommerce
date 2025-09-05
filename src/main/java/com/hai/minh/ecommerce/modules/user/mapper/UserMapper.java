package com.hai.minh.ecommerce.modules.user.mapper;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

//    @Mapping(source = "roles", target = "roles", qualifiedByName = "roleNames")
//    UserData toEntity(UserEntity entity);
//
//    @Named("roleNames")
//    default Set<String> getRoleNames(Set<RoleEntity> roles) {
//        return roles.stream()
//                .map(it -> it.getRole().name())
//                .collect(Collectors.toSet());
//    }

}
