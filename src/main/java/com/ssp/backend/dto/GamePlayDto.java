package com.ssp.backend.dto;

import com.ssp.backend.enums.GameMove;
import jakarta.validation.Valid;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GamePlayDto {

    @Valid
    private GameMove playerMove;

}
