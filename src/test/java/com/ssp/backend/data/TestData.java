package com.ssp.backend.data;


import com.ssp.backend.dto.UserDto;
import com.ssp.backend.entity.GamePlayEntity;
import com.ssp.backend.entity.UserEntity;
import com.ssp.backend.enums.RoleTypes;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class TestData {

    public static UserDto getTestUser() {
        Set<RoleTypes> roles = new HashSet<>();
        roles.add(RoleTypes.USER);
        return new UserDto(LocalDateTime.now(),"testUser", "testPassword", "test user", "testEmail", 8765445678L, roles, null);
    }

    public static UserDto getTestUser2() {
        Set<RoleTypes> roles = new HashSet<>();
        roles.add(RoleTypes.USER);
        return new UserDto(LocalDateTime.now(),"testUser2", "testPassword2", "test user2", "testEmail2", 8765445678L, roles, null);
    }

    public static String generateUserJSON() {

        return "{    \"userName\" : \"testUser\",\n" +
                "    \"password\": \"testPassword\",\n" +
                "    \"email\":\"testEmail\",\n" +
                "    \"phone\": 8765445678,\n" +
                "    \"fullName\": \"test user\"}";
    }

    public static GamePlayEntity returnGamePlayEntity() {
        return GamePlayEntity.builder()
                .userEntity(null)
                .computerMove("PAPER")
                .playerMove("SCISSORS")
                .winner("WIN")
                .date(LocalDateTime.now())
                .build();
    }

}
