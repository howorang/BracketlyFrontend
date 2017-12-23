package edu.bracketly.frontend.dto;

public class SeatDto {
    private long id;
    private PlayerDto player;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public PlayerDto getPlayer() {
        return player;
    }

    public void setPlayer(PlayerDto player) {
        this.player = player;
    }
}
