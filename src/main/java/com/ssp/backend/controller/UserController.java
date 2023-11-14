package com.ssp.backend.controller;

import com.ssp.backend.dto.UserDto;
import com.ssp.backend.entity.UserEntity;
import com.ssp.backend.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ap1/v1")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping("/register")
    public ResponseEntity<UserEntity> newUser(@Valid @RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.saveUser(userDto));
    }


    @GetMapping("/list/{pageNo}")
    public ResponseEntity<Page<UserEntity>> findPaginated(@PathVariable int pageNo, @RequestParam("pageSize") int pageSize, @RequestParam("sortField") String sortField,
                                                          @RequestParam("sortDir") String sortDir ){

        return ResponseEntity.ok(userService.getAllUsers(pageNo, pageSize, sortField,sortDir));
    }
}
