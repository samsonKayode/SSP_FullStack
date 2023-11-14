package com.ssp.backend.mapper;

import com.ssp.backend.dto.UserDto;
import com.ssp.backend.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "password", ignore = true)
    UserDto toUserDto(UserEntity user);

    UserEntity toUserEntity(UserDto userDto);
}
