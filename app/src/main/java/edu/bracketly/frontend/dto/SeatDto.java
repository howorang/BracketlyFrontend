package edu.bracketly.frontend.dto;

import java.io.Serializable;

public class SeatDto implements Serializable {
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
