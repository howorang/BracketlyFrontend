package edu.bracketly.frontend.dto;


import edu.bracketly.frontend.consts.BRACKET_STATUS;
import lombok.Data;

@Data
public abstract class BracketStateDto {
    private PlayerDto winner;
    private BRACKET_STATUS bracket_status;
}
