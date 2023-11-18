package com.ssp.backend.service;

import com.ssp.backend.enums.GameMove;
import com.ssp.backend.enums.GamePlayResult;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class GamePlaySettingsTest {

    @InjectMocks
    private GamePlaySettings gamePlaySettings;


    @Test
    void processGamePlayToGetATie() {
        GamePlayResult result = gamePlaySettings.processGamePlay(GameMove.PAPER, GameMove.PAPER);
        assertEquals(GamePlayResult.TIE, result);
    }

    @Test
    void processGamePlayToGetAWin() {
        GamePlayResult result = gamePlaySettings.processGamePlay(GameMove.PAPER, GameMove.STONE);
        assertEquals(GamePlayResult.WIN, result);
    }

    @Test
    void processGamePlayToGetALoss() {
        GamePlayResult result = gamePlaySettings.processGamePlay(GameMove.PAPER, GameMove.SCISSORS);
        assertEquals(GamePlayResult.LOSS, result);
    }
}