package com.ssp.backend.controller;

import com.ssp.backend.dto.JwtRequest;
import com.ssp.backend.dto.UserDto;
import com.ssp.backend.service.LoginService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/authenticate")
@RequiredArgsConstructor
public class AuthenticationController {

    private final LoginService loginService;

    @PostMapping
    public ResponseEntity<UserDto> authenticate(@Valid @RequestBody JwtRequest authenticationRequest) {
        return new ResponseEntity<>(loginService.createAuthenticationToken(authenticationRequest), HttpStatus.OK);
    }
}
