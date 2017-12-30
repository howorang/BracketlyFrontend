package edu.bracketly.frontend.app.tournament.round;

import android.support.v7.widget.LinearLayoutManager;

import java.util.List;

import javax.inject.Inject;

import edu.bracketly.frontend.api.SingleEliminationBracketApi;
import edu.bracketly.frontend.app.Presenter;
import edu.bracketly.frontend.dto.MatchDto;
import edu.bracketly.frontend.navigation.Navigator;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
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

    private final CompositeDisposable disposable;

    @Inject
    public RoundPresenter(SingleEliminationBracketApi singleEliminationBracketApi) {
        this.singleEliminationBracketApi = singleEliminationBracketApi;
        disposable = new CompositeDisposable();
    }

    @Override
    public void onResume() {
        Disposable subscribe = singleEliminationBracketApi.getRound(bracketId, roundNumber)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(roundDto -> {
                    matchDtos = roundDto.getMatches();
                    if (view.list.getAdapter() == null) {
                        setupAdapter();
                    } else {
                        view.list.getAdapter().notifyDataSetChanged();
                    }
                });
        disposable.add(subscribe);
    }

    private void setupAdapter() {
        view.list.setAdapter(new MyMatchRecyclerViewAdapter(this));
        view.list.setLayoutManager(new LinearLayoutManager(view.getContext()));
    }

    @Override
    public void onDestroy() {
        disposable.clear();
    }

    public int getMatchCount() {
        return matchDtos != null ? matchDtos.size() : 0;
    }

    public String getPlayerTwoLabel(int position) {
        return getPlayerLabel(matchDtos.get(position), 2);
    }

    public String getPlayerOneLabel(int position) {
        return getPlayerLabel(matchDtos.get(position), 1);
    }

    private String getPlayerLabel(MatchDto matchDto, int playerNumber) {
        if (matchDto.getSeats().get(playerNumber - 1).getPlayer() != null) {
            return matchDto.getSeats().get(playerNumber - 1).getPlayer().getUsername();
        } else {
            return "Unknown";
        }
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
