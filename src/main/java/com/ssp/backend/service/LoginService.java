package com.ssp.backend.service;


import com.ssp.backend.dto.JwtRequest;
import com.ssp.backend.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public interface LoginService {

    public UserDto createAuthenticationToken(JwtRequest authenticationRequest);
}
