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
import com.ssp.backend.repository.UserDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
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
    private final UserDao userDao;


    @Override
    public GamePlayEntity saveGamePlay(GamePlayDto gamePlayDto) {
        GamePlayEntity gamePlayEntity = new GamePlayEntity();
        Optional<UserEntity> userEntity = userDao.findByUserName(userService.getCurrentUser());
        gamePlayEntity.setUserEntity(userEntity.get());
        gamePlayEntity.setDate(LocalDateTime.now());
        GameMove computerMove = getComputerMove();
        gamePlayEntity.setComputerMove(computerMove.getLabel());
        gamePlayEntity.setPlayerMove(gamePlayDto.getPlayerMove().getLabel());

        GamePlayResult result = gamePlaySettings.processGamePlay(gamePlayDto.getPlayerMove(), computerMove);
        gamePlayEntity.setWinner(result.getLabel());
        log.info("Player ==>"+gamePlayEntity.getUserEntity().getUserName() +" played ==>"+gamePlayEntity.getPlayerMove()+ ". Game Result ===>"+result.getLabel());

        return gamePlayDao.save(gamePlayEntity);
    }

    @Override
    public Page<GamePlayEntity> getUserGameHistory(int pageNo, int pageSize) {

        String sortField ="date";
        String sortDirection = "desc";

        final Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
                : Sort.by(sortField).descending();
        final Pageable pageable = PageRequest.of(pageNo -1, pageSize, sort);

        Optional<UserEntity> userEntity = userDao.findByUserName(userService.getCurrentUser());

        return gamePlayDao.findByUserEntityOrderByDateDesc(pageable, userEntity.get()).orElseThrow(()-> new CustomException("Unable to retrieve requested data", HttpStatus.NOT_FOUND));
    }

    @Override
    public List<GamePlayEntity> getUserGameHistory() {

        Optional<UserEntity> userEntity = userDao.findByUserName(userService.getCurrentUser());

        return gamePlayDao.findByUserEntityOrderByDateDesc(userEntity.get()).orElseThrow(() -> new CustomException("Unable to retrieve requested data", HttpStatus.BAD_REQUEST));
    }

    private GameMove getComputerMove() {
        Random random = new Random();
        int randomNumber = random.nextInt(3);
        return GameMove.values()[randomNumber];
    }
}
