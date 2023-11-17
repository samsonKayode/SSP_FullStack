package com.ssp.backend.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GameMove {

    STONE("STONE"),
    SCISSORS("PAPER"),
    PAPER("SCISSORS");

    private String label;

}
