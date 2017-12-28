package edu.bracketly.frontend.dto;

import java.io.Serializable;

public class PlayerDto implements Serializable {
    private long id;
    private String username;
    private long rank;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getRank() {
        return rank;
    }

    public void setRank(long rank) {
        this.rank = rank;
    }
}
