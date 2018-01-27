package edu.bracketly.frontend.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SingleBracketStateDto extends BracketStateDto {
    private int currentRound;
    private int roundCount;
}
