package com.ssp.backend.controller;

import com.ssp.backend.data.TestData;
import com.ssp.backend.dto.UserDto;
import com.ssp.backend.entity.UserEntity;
import com.ssp.backend.mapper.UserMapper;
import com.ssp.backend.mapper.UserMapperImpl;
import com.ssp.backend.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @MockBean
    private UserService userService;

    private UserMapper userMapper = new UserMapperImpl();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void createNewUser() throws Exception {
        String jsonString = TestData.generateUserJSON();
        UserDto userDto = TestData.getTestUser();

        UserEntity userEntity = userMapper.toUserEntity(userDto);

        when(userService.saveUser(userDto)).thenReturn(userEntity);

        mockMvc.perform(post("/register").contentType(MediaType.APPLICATION_JSON).content(jsonString))
                .andDo(print())
                .andExpect(status().isOk());
    }

}