package com.ssp.backend.controller;

import com.ssp.backend.dto.GamePlayDto;
import com.ssp.backend.entity.GamePlayEntity;
import com.ssp.backend.service.GamePlayService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/play")
@RequiredArgsConstructor
@Tag(name = "Game Play", description = "GamePlay Controller")
@CrossOrigin
public class GamePlayController {

    private final GamePlayService gamePlayService;

    @PostMapping
    public ResponseEntity<GamePlayEntity> play(@Valid @RequestBody GamePlayDto gamePlayDto) {
        return ResponseEntity.ok(gamePlayService.saveGamePlay(gamePlayDto));
    }

    @GetMapping("/history")
    public ResponseEntity<Page<GamePlayEntity>> userPlayHistory(@RequestParam (value = "pageNo", defaultValue = "1")  int pageNo,
                                                                @RequestParam (value = "pageSize", defaultValue = "10") int pageSize) {
        return ResponseEntity.ok(gamePlayService.getUserGameHistory(pageNo, pageSize));
    }

    @GetMapping("/list")
    public ResponseEntity<List<GamePlayEntity>> userPlayHistory() {
        return ResponseEntity.ok(gamePlayService.getUserGameHistory());
    }


}
