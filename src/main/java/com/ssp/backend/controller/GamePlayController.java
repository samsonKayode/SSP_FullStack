package com.ssp.backend.controller;

import com.ssp.backend.dto.GamePlayDto;
import com.ssp.backend.entity.GamePlayEntity;
import com.ssp.backend.service.GamePlayService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/play")
@RequiredArgsConstructor
@Tag(name = "Game Play", description = "GamePlay Controller")
public class GamePlayController {

    private final GamePlayService gamePlayService;

    @PostMapping
    public ResponseEntity<GamePlayEntity> play(@Valid @RequestBody GamePlayDto gamePlayDto) {

        return ResponseEntity.ok(gamePlayService.saveGamePlay(gamePlayDto));
    }
}
