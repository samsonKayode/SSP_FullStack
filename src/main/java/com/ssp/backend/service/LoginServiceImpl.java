package com.ssp.backend.service;

import com.ssp.backend.dto.JwtRequest;
import com.ssp.backend.dto.UserDto;
import com.ssp.backend.entity.UserEntity;
import com.ssp.backend.exception.CustomException;
import com.ssp.backend.jwt.UserAuthenticationProvider;
import com.ssp.backend.mapper.UserMapper;
import com.ssp.backend.repository.UserDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService{
    private final UserAuthenticationProvider jwtTokenUtil;
    private final UserMapper userMapper;
    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDto createAuthenticationToken(JwtRequest jwtRequest) {

        UserEntity userEntity = userDao.findByUserName(jwtRequest.getUserName()).orElseThrow(() -> new CustomException("invalid login credentials ("+jwtRequest.getUserName()+")", HttpStatus.NOT_FOUND));
        if (passwordEncoder.matches(CharBuffer.wrap(jwtRequest.getPassword()), userEntity.getPassword())) {
            UserDto userDetails = userMapper.toUserDto(userEntity);
            final String token = jwtTokenUtil.createToken(userDetails);
            userDetails.setToken(token);
            log.info("user: "+userDetails.getUserName()+" logged in at - "+ LocalDateTime.now());
            return userDetails;
        }else{
            throw new CustomException("You have entered invalid login credentials ("+jwtRequest.getUserName()+")", HttpStatus.BAD_REQUEST);
        }

    }
}
