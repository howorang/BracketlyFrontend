package edu.bracketly.frontend.app.tournament.details;

import android.os.Bundle;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import javax.inject.Inject;

import edu.bracketly.frontend.R;
import edu.bracketly.frontend.api.BracketApi;
import edu.bracketly.frontend.api.SingleEliminationBracketApi;
import edu.bracketly.frontend.api.TournamentApi;
import edu.bracketly.frontend.app.BasePresenter;
import edu.bracketly.frontend.app.UserContextHelper;
import edu.bracketly.frontend.app.ranking.PlayerListFragment;
import edu.bracketly.frontend.app.tournament.round.RoundPagerAdapter;
import edu.bracketly.frontend.app.tournament.round.RoundPresenter;
import edu.bracketly.frontend.consts.TOURNAMENT_STATUS;
import edu.bracketly.frontend.dto.SingleBracketStateDto;
import edu.bracketly.frontend.dto.TournamentDto;
import edu.bracketly.frontend.navigation.Navigator;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by howor on 25.12.2017.
 */

public class TournamentDetailsPresenter extends BasePresenter<TournamentDetailsFragment> {

    private static final SimpleDateFormat hourFormat = new SimpleDateFormat("HH::mm", Locale.getDefault());
    private static final SimpleDateFormat dayFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());

    private static final String SELECTED_ROUND = "selectedRound";

    private long tournamentId;
    private TournamentDto tournament;
    private SingleBracketStateDto bracketStateDto;
    private TournamentApi tournamentApi;
    private BracketApi bracketApi;
    private SingleEliminationBracketApi singleEliminationBracketApi;
    private RoundPagerAdapter roundPagerAdapter;
    private UserContextHelper userContextHelper;

    @Inject
    protected TournamentDetailsPresenter(TournamentDetailsFragment view, TournamentApi tournamentApi, BracketApi bracketApi,
                                         SingleEliminationBracketApi singleEliminationBracketApi,
                                         UserContextHelper userContextHelper) {
        super(view);
        this.tournamentApi = tournamentApi;
        this.bracketApi = bracketApi;
        this.singleEliminationBracketApi = singleEliminationBracketApi;
        this.userContextHelper = userContextHelper;
    }

    @Override
    public void onResume() {
        loadTournament(tournamentId);
    }

    private void loadTournament(long tournamentId) {
        Disposable subscribe = tournamentApi.getTournamentDetails(tournamentId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(tournamentDto -> {
                            tournament = tournamentDto;
                            updateUi();
                            if (tournament.getTournamentStatus() != TOURNAMENT_STATUS.PLANNING) {
                                view.setPlanningMode(false);
                                loadBracketDetails();
                            } else {
                                view.setPlanningMode(true);
                                loadJoinedPlayers();
                            }
                        },
                        this::handleError);
        disposable.add(subscribe);
    }

    public Long getBracketId() {
        return tournament.getBracketId();
    }

    public Integer getRoundCount() {
        return bracketStateDto.getRoundCount();
    }

    private void loadBracketDetails() {
        if (tournament.getBracketId() != null) {
            Disposable subscribe = bracketApi.getBracketState(tournament.getBracketId())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(dto -> {
                                bracketStateDto = (SingleBracketStateDto) dto;
                                if (view.viewPager.getAdapter() == null) {
                                    view.viewPager.setAdapter(
                                            new RoundPagerAdapter(view.getFragmentManager(), this));
                                }
                            },
                            this::handleError);
            disposable.add(subscribe);
        }
    }

    private void updateUi() {
        updateActionBar();
        view.toolbarLayout.setTitle(getTitle());
        view.eventHour.setText(hourFormat.format(tournament.getEventDate()));
        view.eventDay.setText(dayFormat.format(tournament.getEventDate()));
        view.eventStatus.setText(tournament.getTournamentStatus().friendlyName);
    }

    private void updateActionBar() {
        view.invalidateActionBarMenu();

    }

    private boolean isTournamentOwner() {
        return userContextHelper.getCurrentUser().getId()
                == tournament.getOrganizerId();
    }

    private void loadJoinedPlayers() {
        Disposable subscribe = tournamentApi.getTournamentPlayers(tournamentId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((playerDtos) -> {
                    view.replaceFragment(R.id.fragment_container,
                            PlayerListFragment.newInstance(new ArrayList<>(playerDtos)));
                }, this::handleError);
        disposable.add(subscribe);
    }

    public String getTitle() {
        return tournament.getName();
    }

    public void setTournamentId(long tournamentId) {
        this.tournamentId = tournamentId;
    }

    void onJoinButtonClick() {
        Disposable subscribe = tournamentApi.joinTournament(tournamentId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                    view.displayTournamentJoinedMessage();
                    onResume();
                }, this::handleError);
        disposable.add(subscribe);
    }

    void onModifyButtonClick() {
        Navigator.openModifyTournamentActivity(view.getContext(), tournament);
    }

    void onStartButtonClick() {
        Disposable subscribe = tournamentApi.startTournament(tournamentId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(dto -> {
                            if (view == null) return;
                            view.displayTournamentStartedMessage();
                            onResume();
                        }
                        , this::handleError);
        disposable.add(subscribe);
    }

    public RoundPresenter getRoundPresenter() {
        return new RoundPresenter(singleEliminationBracketApi, isTournamentOwner(), gson);
    }

    public boolean showJoinOption() {
        return tournament != null && tournament.getTournamentStatus() == TOURNAMENT_STATUS.PLANNING;
    }

    public boolean showEditOption() {
        return tournament != null && isTournamentOwner() && tournament.getTournamentStatus() == TOURNAMENT_STATUS.PLANNING;
    }

    public boolean showStartOption() {
        return tournament != null && tournament.getTournamentStatus() == TOURNAMENT_STATUS.PLANNING;
    }

    public void restoreState(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(SELECTED_ROUND)) {
                view.viewPager.setCurrentItem(savedInstanceState.getInt(SELECTED_ROUND));
            }
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(SELECTED_ROUND, view.viewPager.getCurrentItem());
    }
}
