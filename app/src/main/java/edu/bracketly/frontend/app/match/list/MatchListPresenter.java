package edu.bracketly.frontend.app.match.list;

import edu.bracketly.frontend.app.Presenter;

/**
 * Created by Piotr Borczyk on 02.01.2018.
 */

public interface MatchListPresenter extends Presenter {
    int getMatchCount();
    void onMatchClick(int position);
    long getMatchId(int position);
    String getPlayerTwoLabel(int position);
    String getPlayerOneLabel(int position);
}
