package edu.bracketly.frontend.dto;

import java.io.Serializable;

/**
 * Created by howor on 23.12.2017.
 */

public class UserDetailsDto implements Serializable {
    private String user;
    private long rank;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
