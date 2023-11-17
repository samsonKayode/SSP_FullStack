package com.ssp.backend.mapper;

import com.ssp.backend.dto.UserDto;
import com.ssp.backend.entity.UserEntity;
import com.ssp.backend.enums.RoleTypes;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-17T04:13:43+0100",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.9 (Azul Systems, Inc.)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto toUserDto(UserEntity user) {
        if ( user == null ) {
            return null;
        }

        UserDto.UserDtoBuilder userDto = UserDto.builder();

        userDto.date( user.getDate() );
        userDto.userName( user.getUserName() );
        userDto.fullName( user.getFullName() );
        userDto.email( user.getEmail() );
        userDto.phone( user.getPhone() );
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

        userEntity.setDate( userDto.getDate() );
        userEntity.setUserName( userDto.getUserName() );
        userEntity.setPassword( userDto.getPassword() );
        userEntity.setFullName( userDto.getFullName() );
        userEntity.setEmail( userDto.getEmail() );
        userEntity.setPhone( userDto.getPhone() );
        Set<RoleTypes> set = userDto.getRoles();
        if ( set != null ) {
            userEntity.setRoles( new LinkedHashSet<RoleTypes>( set ) );
        }

        return userEntity;
    }
}
