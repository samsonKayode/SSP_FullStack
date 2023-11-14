package com.ssp.backend.service;

import com.ssp.backend.dto.GamePlayDto;
import com.ssp.backend.entity.GamePlayEntity;
import org.springframework.data.domain.Page;

public interface GamePlayService {

    GamePlayEntity saveGamePlay(GamePlayDto gamePlayDto);

    Page<GamePlayEntity> getAllGameHistory(int pageNo, int pageSize, String sortField, String sortDirection);

    Page<GamePlayEntity> getUserGameHistory(int pageNo, int pageSize, String sortField, String sortDirection);

}
