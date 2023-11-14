package com.ssp.backend.repository;


import com.ssp.backend.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDao extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUserName(String userName);

    UserEntity save(UserEntity userEntity);

    Boolean existsByUserName(String userName);
}
