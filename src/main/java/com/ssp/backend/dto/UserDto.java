package com.ssp.backend.dto;

import com.ssp.backend.enums.RoleTypes;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserDto {
    @NotNull
    private String userName;
    @NotNull
    @Length (min = 5)
    private String password;
    @NotNull
    private String fullName;
    @NotNull
    private Set<RoleTypes> roles;
}
