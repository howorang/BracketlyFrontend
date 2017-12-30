package edu.bracketly.frontend.app;

import edu.bracketly.frontend.dto.UserDto;

/**
 * Created by howor on 30.12.2017.
 */

public class UserContextHelper {
    private UserDto currentUser;

    public UserDto getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(UserDto currentUser) {
        this.currentUser = currentUser;
    }
}
