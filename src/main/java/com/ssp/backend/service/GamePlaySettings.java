package com.ssp.backend.service;

import com.ssp.backend.enums.GameMove;
import com.ssp.backend.enums.GamePlayResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class GamePlaySettings {

    private boolean isPlayerWin(GameMove playerMove, GameMove computerMove) {
        return playerMove.equals(GameMove.STONE) && computerMove.equals(GameMove.SCISSORS)
                || (playerMove.equals(GameMove.SCISSORS) && computerMove.equals(GameMove.PAPER))
                || (playerMove.equals(GameMove.PAPER) && computerMove.equals(GameMove.STONE));
    }

    public GamePlayResult processGamePlay(GameMove playerMove, GameMove computerMove) {
        log.info("Player's Move ===> "+playerMove+" *** Computer move ===> " + computerMove);

        if (playerMove.equals(computerMove)) {
            log.info("It's a tie!");
            return GamePlayResult.TIE;
        } else if (isPlayerWin(playerMove, computerMove)) {
            log.info("Player won!");
            return GamePlayResult.WIN;
        } else {
            log.info("You lost!");
            return GamePlayResult.LOSS;
        }
    }
}
