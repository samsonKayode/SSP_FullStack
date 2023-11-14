package com.ssp.backend.service;

import com.ssp.backend.dto.GamePlayDto;
import com.ssp.backend.dto.UserDto;
import com.ssp.backend.entity.GamePlayEntity;
import com.ssp.backend.entity.UserEntity;
import com.ssp.backend.enums.GameMove;
import com.ssp.backend.enums.GamePlayResult;
import com.ssp.backend.exception.CustomException;
import com.ssp.backend.mapper.GamePlayMapper;
import com.ssp.backend.mapper.UserMapper;
import com.ssp.backend.repository.GamePlayDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@Slf4j
@RequiredArgsConstructor
public class GamePlayServiceImpl implements GamePlayService {

    private final UserService userService;
    private final GamePlayDao gamePlayDao;
    private final GamePlaySettings gamePlaySettings;
    private final GamePlayMapper gamePlayMapper;
    private final UserMapper userMapper;


    @Override
    public GamePlayEntity saveGamePlay(GamePlayDto gamePlayDto) {
        GamePlayEntity gamePlayEntity = gamePlayMapper.toGamePlayEntity(gamePlayDto);
        UserDto userDto = userService.findByUsername(userService.getCurrentUser());
        gamePlayEntity.setUserEntity(userMapper.toUserEntity(userDto));
        gamePlayEntity.setDate(LocalDateTime.now());
        String computerMove = getComputerMove();
        gamePlayEntity.setComputerMove(GameMove.valueOf(computerMove));

        GamePlayResult result = gamePlaySettings.processGamePlay(gamePlayEntity.getPlayerMove().getLabel(), computerMove);
        gamePlayEntity.setWinner(result);
        log.info("Player ==>"+gamePlayEntity.getUserEntity().getUserName() +" played ==>"+gamePlayEntity.getPlayerMove()+ ". Game Result ===>"+result.getLabel());

        return gamePlayDao.save(gamePlayEntity);
    }

    @Override
    public Page<GamePlayEntity> getAllGameHistory(int pageNo, int pageSize, String sortField, String sortDirection) {
        return null;
    }

    @Override
    public Page<GamePlayEntity> getUserGameHistory(int pageNo, int pageSize, String sortField, String sortDirection) {
        final Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
                : Sort.by(sortField).descending();
        final Pageable pageable = PageRequest.of(pageNo -1, pageSize, sort);

        UserDto userDto = userService.findByUsername(userService.getCurrentUser());

        UserEntity userEntity = userMapper.toUserEntity(userDto);

        return gamePlayDao.findByUserEntityOrderByDateDesc(pageable, userEntity).orElseThrow(()-> new CustomException("Unable to retrieve requested data", HttpStatus.NOT_FOUND));
    }

    private String getComputerMove() {
        Random random = new Random();
        int randomNumber = random.nextInt(3);
        String computerMove = GameMove.values()[randomNumber].getLabel();
        return computerMove;
    }
}
