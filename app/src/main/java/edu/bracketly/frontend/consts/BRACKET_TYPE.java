package edu.bracketly.frontend.consts;

/**
 * Created by howor on 22.12.2017.
 */

public enum BRACKET_TYPE {
    SINGLE_ELIMINATION("Single elimination bracket");

    public final String firendlyName;

    BRACKET_TYPE(String friendlyName) {
        this.firendlyName = friendlyName;
    }


    @Override
    public String toString() {
        return firendlyName;
    }
}
