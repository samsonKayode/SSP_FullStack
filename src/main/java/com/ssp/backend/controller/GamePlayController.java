package com.ssp.backend.controller;

import com.ssp.backend.dto.GamePlayDto;
import com.ssp.backend.entity.GamePlayEntity;
import com.ssp.backend.service.GamePlayService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/play")
@RequiredArgsConstructor
public class GamePlayController {

    private final GamePlayService gamePlayService;

    @PostMapping
    public ResponseEntity<GamePlayEntity> play(@Valid @RequestBody GamePlayDto gamePlayDto) {

        return ResponseEntity.ok(gamePlayService.saveGamePlay(gamePlayDto));
    }
}
