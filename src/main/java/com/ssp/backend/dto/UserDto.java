package com.ssp.backend.dto;

import com.ssp.backend.enums.RoleTypes;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserDto {
    private LocalDateTime date;
    @NotNull(message = "username cannot be empty")
    @Size(min = 3)
    @NotBlank
    private String userName;
    @NotNull
    @Size (min = 5)
    @NotBlank
    private String password;
    @NotNull
    @Size(min = 4)
    @NotBlank
    private String fullName;
    private String email;
    private Long phone;
    private Set<RoleTypes> roles;
    private String token;
}
