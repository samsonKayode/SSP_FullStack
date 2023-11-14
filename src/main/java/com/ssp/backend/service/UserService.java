package com.ssp.backend.service;

import com.ssp.backend.dto.UserDto;
import com.ssp.backend.entity.UserEntity;
import org.springframework.data.domain.Page;

public interface UserService {

    UserEntity saveUser (UserDto userDTO);

    UserEntity updateUser(UserDto userDTO, Long id);

    //UserEntity findByUsername(String userName);

    UserDto findByUsername(String userName);

    Page<UserEntity> getAllUsers(int pageNo, int pageSize, String sortField, String sortDirection);

}
