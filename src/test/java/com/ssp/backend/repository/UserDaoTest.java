package com.ssp.backend.repository;

import com.ssp.backend.data.TestData;
import com.ssp.backend.entity.UserEntity;
import com.ssp.backend.mapper.UserMapper;
import com.ssp.backend.mapper.UserMapperImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserDaoTest {

    @Autowired
    private UserDao userDao;
    private UserEntity user;
    private final UserMapper userMapper = new UserMapperImpl();
    @BeforeEach
    public void setup() {
        user = userMapper.toUserEntity(TestData.getTestUser());
        userDao.save(user);
    }

    public static Stream input() {
        return Stream.of(
                Arguments.of("testUser", true),
                Arguments.of("test", false)
        );
    }

    @ParameterizedTest
    @MethodSource("input")
    void findByUserName(String input, boolean expected) {
        Optional<UserEntity> user = userDao.findByUserName(input);
        assertEquals(expected, user.isPresent());

    }

    @ParameterizedTest
    @MethodSource("input")
    void existsByUserName(String input, boolean expected) {
        boolean result = userDao.existsByUserName(input);
        assertEquals(expected, result);
    }

    @Test
    public void saveNewUser() {
        UserEntity result = userDao.save(user);
        assertEquals(user, result);
    }


}