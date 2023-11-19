package com.ssp.backend.controller;

import com.ssp.backend.dto.UserDto;
import com.ssp.backend.entity.UserEntity;
import com.ssp.backend.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "User", description = "User Controller")
public class UserController {

    private final UserService userService;


    @Operation(summary = "Register a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "New user registered",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDto.class)) }),
            @ApiResponse(responseCode = "401", description = "You are not authorized",
                    content = @Content),
            @ApiResponse(responseCode = "406", description = "Duplicate data entered",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "You have entered invalid data",
                    content = @Content)})
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
