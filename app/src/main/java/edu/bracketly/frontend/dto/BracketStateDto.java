package edu.bracketly.frontend.dto;


import java.io.Serializable;

import edu.bracketly.frontend.consts.BRACKET_STATUS;
import lombok.Data;

@Data
public abstract class BracketStateDto implements Serializable {
    private PlayerDto winner;
    private BRACKET_STATUS bracket_status;
}
