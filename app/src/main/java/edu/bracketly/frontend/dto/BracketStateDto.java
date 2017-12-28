package edu.bracketly.frontend.dto;


import java.io.Serializable;

import edu.bracketly.frontend.consts.BRACKET_STATUS;

public abstract class BracketStateDto implements Serializable {
    private PlayerDto winner;
    private BRACKET_STATUS bracket_status;

    public PlayerDto getWinner() {
        return winner;
    }

    public void setWinner(PlayerDto winner) {
        this.winner = winner;
    }

    public BRACKET_STATUS getBracket_status() {
        return bracket_status;
    }

    public void setBracket_status(BRACKET_STATUS bracket_status) {
        this.bracket_status = bracket_status;
    }
}
