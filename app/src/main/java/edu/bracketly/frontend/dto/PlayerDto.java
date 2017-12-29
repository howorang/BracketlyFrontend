package edu.bracketly.frontend.dto;

public class PlayerDto extends UserDto {
    private long rank;
    private int gamesPlayed;

    public long getRank() {
        return rank;
    }

    public void setRank(long rank) {
        this.rank = rank;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }
}
