package com.ssp.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/verify")
public class VerificationController {

    @GetMapping
    public ResponseEntity<Boolean> verify() {
        return ResponseEntity.ok(true);
    }
}
