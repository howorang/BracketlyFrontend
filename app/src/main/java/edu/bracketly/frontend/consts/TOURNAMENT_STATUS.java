package edu.bracketly.frontend.consts;

/**
 * Created by howor on 22.12.2017.
 */

public enum TOURNAMENT_STATUS {
    PLANNING("In planning"),
    LIVE("Live"),
    ENDED("Played out");

    public final String friendlyName;

    TOURNAMENT_STATUS(String friendlyName) {
        this.friendlyName = friendlyName;
    }
}
