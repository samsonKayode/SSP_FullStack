package com.ssp.backend.service;

import com.ssp.backend.dto.GamePlayDto;
import com.ssp.backend.entity.GamePlayEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface GamePlayService {

    GamePlayEntity saveGamePlay(GamePlayDto gamePlayDto);

    Page<GamePlayEntity> getUserGameHistory(int pageNo, int pageSize);

    List<GamePlayEntity> getUserGameHistory();

}
