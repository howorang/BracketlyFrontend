package edu.bracketly.frontend.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import edu.bracketly.frontend.app.tournament.round.RoundFragment;

/**
 * Created by howor on 26.12.2017.
 */

public class RoundPagerAdapter extends FragmentPagerAdapter {

    private long bracketId;
    private int roundCount;

    public RoundPagerAdapter(FragmentManager fm, long bracketId, int roundCount) {
        super(fm);
        this.bracketId = bracketId;
        this.roundCount = roundCount;
    }

    @Override
    public Fragment getItem(int position) {
        return RoundFragment.newInstance(bracketId, position);
    }

    @Override
    public int getCount() {
        return roundCount;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return String.valueOf(position);
    }
}
