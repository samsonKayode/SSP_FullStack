package com.ssp.backend.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GamePlayResult {
    WIN("WIN"),
    LOSS("LOSS"),
    TIE("TIE");
    private String label;
}
