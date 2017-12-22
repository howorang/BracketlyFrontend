package edu.bracketly.frontend.dto;

import lombok.Data;

@Data
public class SingleBracketStateDto extends BracketStateDto {
    private int currentRound;
}
