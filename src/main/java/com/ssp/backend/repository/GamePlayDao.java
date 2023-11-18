package com.ssp.backend.repository;

import com.ssp.backend.entity.GamePlayEntity;
import com.ssp.backend.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GamePlayDao extends JpaRepository<GamePlayEntity, Long> {

    Optional<Page<GamePlayEntity>> findByUserEntityOrderByDateDesc(Pageable pageable, UserEntity userEntity);

    Optional<List<GamePlayEntity>> findByUserEntityOrderByDateDesc(UserEntity userEntity);
}
