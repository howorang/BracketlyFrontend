package edu.bracketly.frontend.consts;

/**
 * Created by howor on 22.12.2017.
 */

public enum SEEDING_STRATEGY {
    RANDOM("Random"),
    BEST_WORST("Worst with best");

    public final String friendlyName;

    SEEDING_STRATEGY(String friendlyName) {
        this.friendlyName = friendlyName;
    }


    @Override
    public String toString() {
        return friendlyName;
    }
}
