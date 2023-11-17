package com.ssp.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class JwtRequest {
    @NotBlank(message = "you have not entered a username")
    @Size(min = 3)
    private String userName;
    @NotBlank(message = "you have not entered a valid password")
    @Size(min = 3)
    private String password;
}
