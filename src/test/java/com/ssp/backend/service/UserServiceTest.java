package com.ssp.backend.service;

import com.ssp.backend.data.TestData;
import com.ssp.backend.dto.UserDto;
import com.ssp.backend.entity.UserEntity;
import com.ssp.backend.mapper.UserMapper;
import com.ssp.backend.mapper.UserMapperImpl;
import com.ssp.backend.repository.UserDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserDao userDAO;
    private UserMapper userMapper = new UserMapperImpl();
    @Spy
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService = new UserServiceImpl(userMapper);


    @BeforeEach
    public void setup() {
        passwordEncoder = new BCryptPasswordEncoder();
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void saveUser() {
        UserDto userDTO = TestData.getTestUser();
        userService.saveUser(userDTO);

        ArgumentCaptor<UserEntity> userEntityArgumentCaptor = ArgumentCaptor.forClass(UserEntity.class);
        verify(userDAO).save(userEntityArgumentCaptor.capture());

        UserEntity argumentCaptureValue = userEntityArgumentCaptor.getValue();
        assertEquals(true, passwordEncoder.matches(userDTO.getPassword(), argumentCaptureValue.getPassword()));

    }

}