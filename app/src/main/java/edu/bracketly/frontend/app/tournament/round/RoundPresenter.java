package edu.bracketly.frontend.app.tournament.round;

import android.support.v7.widget.LinearLayoutManager;

import java.util.List;

import javax.inject.Inject;

import edu.bracketly.frontend.api.SingleEliminationBracketApi;
import edu.bracketly.frontend.app.Presenter;
import edu.bracketly.frontend.dto.MatchDto;
import edu.bracketly.frontend.navigation.Navigator;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by howor on 27.12.2017.
 */

public class RoundPresenter implements Presenter {

    private List<MatchDto> matchDtos;
    private SingleEliminationBracketApi singleEliminationBracketApi;
    long bracketId;
    int roundNumber;
    private RoundFragment view;

    @Inject
    public RoundPresenter(SingleEliminationBracketApi singleEliminationBracketApi) {
        this.singleEliminationBracketApi = singleEliminationBracketApi;
    }

    @Override
    public void onResume() {
        singleEliminationBracketApi.getRound(bracketId, roundNumber)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(roundDto -> {
                    matchDtos = roundDto.getMatches();
                    setupAdapter();
                });
    }

    private void setupAdapter() {
        view.list.setAdapter(new MyMatchRecyclerViewAdapter(this));
        view.list.setLayoutManager(new LinearLayoutManager(view.getContext()));
    }

    @Override
    public void onDestroy() {

    }

    public int getMatchCount() {
        return matchDtos.size();
    }

    public String getPlayerTwoLabel(int position) {
        return matchDtos.get(position).getPlayers().get(0).getPlayer().getUsername();
    }

    public String getPlayerOneLabel(int position) {
        return matchDtos.get(position).getPlayers().get(1).getPlayer().getUsername();
    }

    public void setView(RoundFragment view) {
        this.view = view;
    }

    public void onMatchClick(int position) {
        Navigator.openMatchActivity(view.getContext(), bracketId, matchDtos.get(position));
    }

    public long getMatchId(int position) {
        return matchDtos.get(position).getId();
    }
}
