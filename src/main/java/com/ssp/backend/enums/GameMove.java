package com.ssp.backend.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GameMove {

    STONE("stone"),
    SCISSORS("scissors"),
    PAPER("paper");

    private String label;

}
