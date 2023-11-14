package com.ssp.backend.mapper;

import com.ssp.backend.dto.GamePlayDto;
import com.ssp.backend.entity.GamePlayEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GamePlayMapper {
    GamePlayDto toGamePlayDto(GamePlayEntity gamePlayEntity);
    GamePlayEntity toGamePlayEntity(GamePlayDto gamePlayDto);
}
