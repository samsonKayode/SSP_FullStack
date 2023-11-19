package com.ssp.backend.controller;

import com.ssp.backend.data.TestData;
import com.ssp.backend.dto.GamePlayDto;
import com.ssp.backend.entity.GamePlayEntity;
import com.ssp.backend.enums.GameMove;
import com.ssp.backend.service.GamePlayService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(GamePlayController.class)
class GamePlayControllerTest {

    @MockBean
    private GamePlayService gamePlayService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    void setUp() {
            mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    @WithMockUser(value = "spring")
    void testPlayGame() throws Exception {

        String jsonString = "{\"playerMove\":\"SCISSORS\"}";
        GamePlayDto gamePlayDto = new GamePlayDto(GameMove.SCISSORS);

        when(gamePlayService.saveGamePlay(gamePlayDto)).thenReturn(TestData.returnGamePlayEntity());

        mockMvc.perform(post("/play").contentType(MediaType.APPLICATION_JSON).content(jsonString))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.computerMove").value("PAPER"))
                .andExpect(jsonPath("$.playerMove").value("SCISSORS"));
    }

    @Test
    @WithMockUser
    void testGamePlayHistory() throws Exception {
        GamePlayEntity gamePlayEntity1 = TestData.returnGamePlayEntity();
        GamePlayEntity gamePlayEntity2 = TestData.returnGamePlayEntity();
        GamePlayEntity gamePlayEntity3 = TestData.returnGamePlayEntity();
        GamePlayEntity gamePlayEntity4 = TestData.returnGamePlayEntity();

        when(gamePlayService.getUserGameHistory()).thenReturn(new ArrayList<GamePlayEntity>(Arrays.asList(gamePlayEntity1, gamePlayEntity2, gamePlayEntity3, gamePlayEntity4)));

        mockMvc.perform(get("/play/list").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(4)));
    }
}