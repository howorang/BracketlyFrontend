package edu.bracketly.frontend.dto;

import java.util.List;

public class SingleBracketStateDto extends BracketStateDto {
    private int currentRound;
    private List<RoundDto> rounds;

    public int getCurrentRound() {
        return currentRound;
    }

    public void setCurrentRound(int currentRound) {
        this.currentRound = currentRound;
    }

    public List<RoundDto> getRounds() {
        return rounds;
    }

    public void setRounds(List<RoundDto> rounds) {
        this.rounds = rounds;
    }
}
