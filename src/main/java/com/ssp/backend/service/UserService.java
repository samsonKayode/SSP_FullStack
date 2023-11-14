package com.ssp.backend.service;

import com.ssp.backend.dto.UserDto;
import com.ssp.backend.entity.UserEntity;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface UserService {

    UserEntity saveUser (UserDto userDTO);

    UserEntity updateUser(UserDto userDTO, Long id);

    Optional<UserDto> findByUsername(String userName);

    Page<UserEntity> getAllUsers(int pageNo, int pageSize, String sortField, String sortDirection);

    String getCurrentUser();

}
