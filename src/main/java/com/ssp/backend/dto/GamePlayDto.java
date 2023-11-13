package com.ssp.backend.dto;

import com.ssp.StoneScissorsPaper.enums.GameMove;
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
    @Valid
    private GameMove computerMove;
}
