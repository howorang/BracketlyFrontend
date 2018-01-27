package edu.bracketly.frontend.app.tournament.round;

import android.support.v7.widget.LinearLayoutManager;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import edu.bracketly.frontend.R;
import edu.bracketly.frontend.api.SingleEliminationBracketApi;
import edu.bracketly.frontend.app.match.list.MatchListPresenter;
import edu.bracketly.frontend.app.match.list.MyMatchRecyclerViewAdapter;
import edu.bracketly.frontend.dto.ApiError;
import edu.bracketly.frontend.dto.MatchDto;
import edu.bracketly.frontend.dto.PlayerDto;
import edu.bracketly.frontend.navigation.Navigator;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

/**
 * Created by howor on 27.12.2017.
 */

public class RoundPresenter implements MatchListPresenter {

    private List<MatchDto> matchDtos;
    private SingleEliminationBracketApi singleEliminationBracketApi;
    long bracketId;
    int roundNumber;
    private RoundFragment view;
    private boolean canEditTournament;
    private Gson gson;

    private final CompositeDisposable disposable;

    @Inject
    public RoundPresenter(SingleEliminationBracketApi singleEliminationBracketApi, boolean canEditTournament, Gson gson) {
        this.singleEliminationBracketApi = singleEliminationBracketApi;
        this.canEditTournament = canEditTournament;
        disposable = new CompositeDisposable();
        this.gson = gson;
    }

    @Override
    public void onResume() {
        Disposable subscribe = singleEliminationBracketApi.getRound(bracketId, roundNumber)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(roundDto -> {
                    if (view == null) return;
                    matchDtos = roundDto.getMatches();
                    if (view.list.getAdapter() == null) {
                        setupAdapter();
                    } else {
                        view.list.getAdapter().notifyDataSetChanged();
                    }
                }, this::handleError);
        disposable.add(subscribe);
    }

    protected void handleError(Throwable e) {
        view.displayMessage(getErrorMessage(e));
    }

    protected String getErrorMessage(Throwable throwable) {
        if (throwable instanceof HttpException) {
            HttpException httpException = (HttpException) throwable;
            switch (httpException.code()) {
                case 401:
                    return "Bad credentials";
                case 500:
                    String string = null;
                    try {
                        string = httpException.response().errorBody().string();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    ApiError apiError = gson.fromJson(string, ApiError.class);
                    return apiError.getMessage();
                default:
                    return "HTTP: " + httpException.code();
            }
        }
        return throwable.getMessage();
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

    public int getPlayerOneResultIconId(int position) {
        return getResultIconResId(matchDtos.get(position), 1);
    }

    public int getPlayerTwoResultIconId(int position) {
        return getResultIconResId(matchDtos.get(position), 2);
    }

    private int getResultIconResId(MatchDto matchDto, int playerNumber) {
        if (matchDto.getWinner() == null) return 0;
        if (matchDto.getSeats().get(playerNumber - 1).getPlayer() != null) {
            PlayerDto player = matchDto.getSeats().get(playerNumber - 1).getPlayer();
            if (player.equals(matchDto.getWinner())) {
                return R.drawable.ic_won;
            } else {
                return R.drawable.ic_lost;
            }
        }
        return 0;
    }

    public void setView(RoundFragment view) {
        this.view = view;
    }

    public void onMatchClick(int position) {
        Navigator.openMatchActivity(view.getContext(), bracketId, matchDtos.get(position),
                canEditTournament);
    }

    public long getMatchId(int position) {
        return matchDtos.get(position).getId();
    }
}
