package com.ssp.backend.data;


import com.ssp.backend.dto.UserDto;
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

    public static String getJsonUser() {

        return "{\"userName\":\"testUser\"" +
                "\"password\":\"testPassword\"" +
                "\"fullName\":\"Test User\"";
    }
}
