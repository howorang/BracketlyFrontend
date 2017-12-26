package edu.bracketly.frontend.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import edu.bracketly.frontend.app.tournament.round.RoundFragment;

/**
 * Created by howor on 26.12.2017.
 */

public class RoundPagerAdapter extends FragmentPagerAdapter {
    public RoundPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return RoundFragment.newInstance(1);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return String.valueOf(position);
    }
}
