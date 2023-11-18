package com.ssp.backend.repository;

import com.ssp.backend.data.TestData;
import com.ssp.backend.entity.GamePlayEntity;
import com.ssp.backend.entity.UserEntity;
import com.ssp.backend.mapper.UserMapper;
import com.ssp.backend.mapper.UserMapperImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GamePlayDaoTest {

    @Autowired
    private UserDao userDao;

    @Autowired
    private GamePlayDao gamePlayDao;
    private final UserMapper userMapper = new UserMapperImpl();
    private UserEntity user;
    private GamePlayEntity gamePlayEntity;
    private GamePlayEntity gamePlayEntity2;

    @BeforeEach
    void setUp() {
        user = userMapper.toUserEntity(TestData.getTestUser());
        userDao.save(user);

        gamePlayEntity = GamePlayEntity.builder()
                .userEntity(user)
                .computerMove("PAPER")
                .playerMove("SCISSORS")
                .winner("WIN")
                .date(LocalDateTime.now())
                .build();

        gamePlayEntity2 = GamePlayEntity.builder()
                .userEntity(user)
                .computerMove("ROCK")
                .playerMove("ROCK")
                .winner("TIE")
                .date(LocalDateTime.now())
                .build();

        gamePlayDao.saveAll(List.of(
                gamePlayEntity, gamePlayEntity2
        ));

    }

    @Test
    void findGamePlayResultForUser() {

        Optional<List<GamePlayEntity>> resultList = gamePlayDao.findByUserEntityOrderByDateDesc(user);
        assertTrue(resultList.get().size() >= 2);
    }

    @Test
    void testGamePlayIsSaved() {
        GamePlayEntity gamePlay = gamePlayDao.save(gamePlayEntity);
        assertEquals("SCISSORS", gamePlay.getPlayerMove());
    }
}