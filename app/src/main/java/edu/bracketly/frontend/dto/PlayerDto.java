package edu.bracketly.frontend.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PlayerDto extends UserDto {
    private long rank;
    private int gamesPlayed;
}
