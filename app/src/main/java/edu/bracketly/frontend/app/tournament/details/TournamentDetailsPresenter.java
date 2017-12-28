package edu.bracketly.frontend.app.tournament.details;

import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.inject.Inject;

import edu.bracketly.frontend.api.BracketApi;
import edu.bracketly.frontend.api.SingleEliminationBracketApi;
import edu.bracketly.frontend.api.TournamentApi;
import edu.bracketly.frontend.app.BasePresenter;
import edu.bracketly.frontend.app.tournament.round.RoundPagerAdapter;
import edu.bracketly.frontend.app.tournament.round.RoundPresenter;
import edu.bracketly.frontend.dto.SingleBracketStateDto;
import edu.bracketly.frontend.dto.TournamentDto;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by howor on 25.12.2017.
 */

public class TournamentDetailsPresenter extends BasePresenter<TournamentDetailsFragment> {

    private static final SimpleDateFormat hourFormat = new SimpleDateFormat("HH::mm", Locale.getDefault());
    private static final SimpleDateFormat dayFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());


    private long tournamentId;
    private TournamentDto tournament;
    private SingleBracketStateDto bracketStateDto;
    private TournamentApi tournamentApi;
    private BracketApi bracketApi;
    private SingleEliminationBracketApi singleEliminationBracketApi;
    private RoundPagerAdapter roundPagerAdapter;

    @Inject
    protected TournamentDetailsPresenter(TournamentDetailsFragment view, TournamentApi tournamentApi, BracketApi bracketApi,
                                         SingleEliminationBracketApi singleEliminationBracketApi) {
        super(view);
        this.tournamentApi = tournamentApi;
        this.bracketApi = bracketApi;
        this.singleEliminationBracketApi = singleEliminationBracketApi;
    }

    @Override
    public void onResume() {
        loadTournament(tournamentId);
    }

    private void loadTournament(long tournamentId) {
        tournamentApi.getTournamentDetails(tournamentId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(tournamentDto -> {
                    tournament = tournamentDto;
                    loadBracketDetails();
                    updateUi();
                });
    }

    public Long getBracketId() {
        return tournament.getBracketId();
    }

    public Integer getRoundCount() {
        return bracketStateDto.getRoundCount();
    }

    private void loadBracketDetails() {
        if (tournament.getBracketId() != null) {
            view.setTournamentHasNotStartedMessage(false);
            bracketApi.getBracketState(tournament.getBracketId())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(dto -> {
                        bracketStateDto = (SingleBracketStateDto) dto;
                        view.viewPager.setAdapter(
                                new RoundPagerAdapter(view.getFragmentManager(), this));
                    });
        } else {
            view.setTournamentHasNotStartedMessage(true);
        }
    }

    private void updateUi() {
        view.toolbarLayout.setTitle(getTitle());
        view.eventHour.setText(hourFormat.format(tournament.getEventDate()));
        view.eventDay.setText(dayFormat.format(tournament.getEventDate()));
    }

    public String getTitle() {
        return tournament.getName();
    }

    public void setTournamentId(long tournamentId) {
        this.tournamentId = tournamentId;
    }

    void onJoinButtonClick() {
        tournamentApi.joinTournament(tournamentId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                    view.displayTournamentJoinedMessage();
                    onResume();
                });
    }

    void onModifyButtonClick() {

    }

    void onStartButtonClick() {
        tournamentApi.startTournament(tournamentId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(dto -> {
                    view.displayTournamentStartedMessage();
                    onResume();
                });
    }

    public RoundPresenter getRoundPresenter() {
        return new RoundPresenter(singleEliminationBracketApi);
    }
}
