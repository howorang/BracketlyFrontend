package edu.bracketly.frontend.dto;

import java.io.Serializable;

/**
 * Created by howor on 29.12.2017.
 */

public class UserDto implements Serializable {
    private long id;
    private String username;

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
}
