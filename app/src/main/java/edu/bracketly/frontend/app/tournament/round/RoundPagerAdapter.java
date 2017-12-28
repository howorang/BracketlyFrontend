package edu.bracketly.frontend.app.tournament.round;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import edu.bracketly.frontend.app.tournament.details.TournamentDetailsPresenter;

/**
 * Created by howor on 26.12.2017.
 */

public class RoundPagerAdapter extends FragmentPagerAdapter {

    TournamentDetailsPresenter presenter;

    public RoundPagerAdapter(FragmentManager fm, TournamentDetailsPresenter presenter) {
        super(fm);
        this.presenter = presenter;
    }

    @Override
    public Fragment getItem(int position) {
        return RoundFragment.newInstance(presenter.getBracketId(), position + 1, presenter.getRoundPresenter());
    }

    @Override
    public int getCount() {
        return presenter.getRoundCount();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return String.valueOf(position + 1);
    }
}
