package com.ssp.backend.controller;

import com.ssp.backend.dto.JwtRequest;
import com.ssp.backend.dto.UserDto;
import com.ssp.backend.service.LoginService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authenticate")
@RequiredArgsConstructor
@Tag(name = "Authenticator", description = "Authentication Controller")
public class AuthenticationController {

    private final LoginService loginService;

    @PostMapping
    public ResponseEntity<UserDto> authenticate(@Valid @RequestBody JwtRequest authenticationRequest) {
        return new ResponseEntity<>(loginService.createAuthenticationToken(authenticationRequest), HttpStatus.OK);
    }
}
