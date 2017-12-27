package edu.bracketly.frontend.dto;

public class SingleBracketStateDto extends BracketStateDto {
    private int currentRound;
    private int roundCount;

    public int getCurrentRound() {
        return currentRound;
    }

    public void setCurrentRound(int currentRound) {
        this.currentRound = currentRound;
    }

    public int getRoundCount() {
        return roundCount;
    }

    public void setRoundCount(int roundCount) {
        this.roundCount = roundCount;
    }
}
