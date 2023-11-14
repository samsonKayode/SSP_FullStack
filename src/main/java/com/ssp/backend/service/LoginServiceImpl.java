package com.ssp.backend.service;

import com.ssp.backend.dto.JwtRequest;
import com.ssp.backend.dto.UserDto;
import com.ssp.backend.entity.UserEntity;
import com.ssp.backend.exception.CustomException;
import com.ssp.backend.exception.InternalServerError;
import com.ssp.backend.jwt.UserAuthenticationProvider;
import com.ssp.backend.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService{
    private final AuthenticationManager authenticationManager;
    private final UserAuthenticationProvider jwtTokenUtil;
    private final UserService userService;
    private UserMapper userMapper;


    @Override
    public UserDto createAuthenticationToken(JwtRequest jwtRequest) {

        authenticate(jwtRequest.getUserName(), jwtRequest.getPassword());
        Optional<UserDto> userDetails = userService.findByUsername(jwtRequest.getUserName());
        final String token = jwtTokenUtil.createToken(userDetails.get());

        userDetails.get().setToken(token);
        log.info("user: "+userDetails.get().getUserName()+" logged in at - "+ LocalDate.now());

        return userDetails.get();
    }

    private void authenticate(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            log.error("user account is disabled ->");
            throw new CustomException("User account is disabled", HttpStatus.BAD_REQUEST);
        } catch (BadCredentialsException e) {
            log.error("invalid credentials provided -->"+username);
            throw new CustomException("invalid credentials provided", HttpStatus.NOT_FOUND);
        }catch (Exception e){
            log.error("internal server error ");
            e.printStackTrace();
            throw new InternalServerError("Internal server error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
