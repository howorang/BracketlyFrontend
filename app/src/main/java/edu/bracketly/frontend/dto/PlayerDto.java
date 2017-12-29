package edu.bracketly.frontend.dto;

public class PlayerDto extends UserDto {
    private long rank;

    public long getRank() {
        return rank;
    }

    public void setRank(long rank) {
        this.rank = rank;
    }
}
