package com.ssp.backend.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GameMove {

    STONE("STONE"),
    SCISSORS("SCISSORS"),
    PAPER("PAPER");

    private String label;

}
