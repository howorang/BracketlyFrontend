package edu.bracketly.frontend.consts;

import edu.bracketly.frontend.R;

/**
 * Created by howor on 22.12.2017.
 */

public enum TOURNAMENT_STATUS {
    PLANNING("In planning", R.drawable.ic_organizer),
    LIVE("Live", R.drawable.ic_live),
    ENDED("Played out", R.drawable.ic_finished);

    public final String friendlyName;
    public final int iconResId;

    TOURNAMENT_STATUS(String friendlyName, int iconResId) {
        this.friendlyName = friendlyName;
        this.iconResId = iconResId;
    }
}
