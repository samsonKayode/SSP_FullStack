package com.ssp.backend.mapper;

import com.ssp.backend.dto.GamePlayDto;
import com.ssp.backend.entity.GamePlayEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-14T18:51:22+0100",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.9 (Azul Systems, Inc.)"
)
@Component
public class GamePlayMapperImpl implements GamePlayMapper {

    @Override
    public GamePlayDto toGamePlayDto(GamePlayEntity gamePlayEntity) {
        if ( gamePlayEntity == null ) {
            return null;
        }

        GamePlayDto gamePlayDto = new GamePlayDto();

        gamePlayDto.setPlayerMove( gamePlayEntity.getPlayerMove() );
        gamePlayDto.setComputerMove( gamePlayEntity.getComputerMove() );

        return gamePlayDto;
    }

    @Override
    public GamePlayEntity toGamePlayEntity(GamePlayDto gamePlayDto) {
        if ( gamePlayDto == null ) {
            return null;
        }

        GamePlayEntity gamePlayEntity = new GamePlayEntity();

        gamePlayEntity.setPlayerMove( gamePlayDto.getPlayerMove() );
        gamePlayEntity.setComputerMove( gamePlayDto.getComputerMove() );

        return gamePlayEntity;
    }
}
