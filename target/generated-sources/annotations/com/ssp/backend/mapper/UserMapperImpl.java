package com.ssp.backend.mapper;

import com.ssp.backend.dto.UserDto;
import com.ssp.backend.entity.UserEntity;
import com.ssp.backend.enums.RoleTypes;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-14T17:57:07+0100",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.9 (Azul Systems, Inc.)"
)
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto toUserDto(UserEntity user) {
        if ( user == null ) {
            return null;
        }

        UserDto.UserDtoBuilder userDto = UserDto.builder();

        userDto.userName( user.getUserName() );
        userDto.password( user.getPassword() );
        userDto.fullName( user.getFullName() );
        Set<RoleTypes> set = user.getRoles();
        if ( set != null ) {
            userDto.roles( new LinkedHashSet<RoleTypes>( set ) );
        }

        return userDto.build();
    }

    @Override
    public UserEntity toUserEntity(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setUserName( userDto.getUserName() );
        userEntity.setPassword( userDto.getPassword() );
        userEntity.setFullName( userDto.getFullName() );
        Set<RoleTypes> set = userDto.getRoles();
        if ( set != null ) {
            userEntity.setRoles( new LinkedHashSet<RoleTypes>( set ) );
        }

        return userEntity;
    }
}
