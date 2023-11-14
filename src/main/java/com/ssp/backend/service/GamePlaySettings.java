package com.ssp.backend.service;

import com.ssp.backend.enums.GameMove;
import com.ssp.backend.enums.GamePlayResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class GamePlaySettings {

    private boolean isPlayerWin(String playerMove, String computerMove) {
        return playerMove.equals(GameMove.STONE.getLabel()) && computerMove.equals(GameMove.SCISSORS.getLabel())
                || (playerMove.equals(GameMove.SCISSORS.getLabel()) && computerMove.equals(GameMove.PAPER.getLabel()))
                || (playerMove.equals(GameMove.PAPER.getLabel()) && computerMove.equals(GameMove.STONE.getLabel()));
    }

    public GamePlayResult processGamePlay(String playerMove, String computerMove) {
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
