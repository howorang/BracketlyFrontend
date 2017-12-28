package edu.bracketly.frontend.dto;

import java.io.Serializable;

public class TournamentStartResponseDto implements Serializable {
    private Long bracketId;

    public Long getBracketId() {
        return bracketId;
    }

    public void setBracketId(Long bracketId) {
        this.bracketId = bracketId;
    }
}
