package edu.bracketly.frontend.dto;

public class SingleBracketStateDto extends BracketStateDto {
    private int currentRound;

    public int getCurrentRound() {
        return currentRound;
    }

    public void setCurrentRound(int currentRound) {
        this.currentRound = currentRound;
    }
}
