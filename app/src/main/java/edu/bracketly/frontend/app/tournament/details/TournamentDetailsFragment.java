package edu.bracketly.frontend.app.tournament.details;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import edu.bracketly.frontend.R;
import edu.bracketly.frontend.app.BaseFragment;


public class TournamentDetailsFragment extends BaseFragment<TournamentDetailsPresenter> {

    public interface Host {
        CollapsingToolbarLayout getToolbarLayout();

        TextView getEventDayText();

        TextView getEventHourText();

        TextView getEventOrganizerText();

        TextView getEventStatusText();
    }

    public static final String TOURNAMENT_ID = "tournament_id";

    CollapsingToolbarLayout toolbarLayout;
    TextView eventDay;
    TextView eventHour;
    TextView eventOragnizer;
    TextView eventStatus;

    @BindView(R.id.fragment_container)
    FrameLayout fragmentContainer;

    @BindView(R.id.pager)
    ViewPager viewPager;

    @BindView(R.id.no_items_message)
    TextView noItemsMessage;

    private Unbinder unbinder;

    public TournamentDetailsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!getArguments().containsKey(TOURNAMENT_ID)) {
            throw new RuntimeException("Bad params!");
        }
        long tournamentId = getArguments().getLong(TOURNAMENT_ID, -11L);
        Host host = (Host) getActivity();
        toolbarLayout = host.getToolbarLayout();
        eventDay = host.getEventDayText();
        eventHour = host.getEventHourText();
        eventStatus = host.getEventStatusText();
        eventOragnizer = host.getEventOrganizerText();
        presenter.setTournamentId(tournamentId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tournament_detail, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        setHasOptionsMenu(true);
        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if (presenter.showJoinOption()) {
            menu.add(0, 0, 0, "Join");
        }
        if (presenter.showEditOption()) {
            menu.add(0, 1, 0, "Edit");
        }
        if (presenter.showStartOption()) {
            menu.add(0, 2, 0, "Start");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 0:
                presenter.onJoinButtonClick();
                break;
            case 1:
                presenter.onModifyButtonClick();
                break;
            case 2:
                presenter.onStartButtonClick();
                break;
        }
        return true;
    }

    void invalidateActionBarMenu() {
        getActivity().invalidateOptionsMenu();
    }

    public void setPlanningMode(boolean planningMode) {
        if (planningMode) {
            viewPager.setVisibility(View.GONE);
        } else {
            viewPager.setVisibility(View.VISIBLE);
        }
    }

    void replaceFragment(@IdRes int containerId, Fragment fragment) {
        getChildFragmentManager().beginTransaction().replace(containerId, fragment).commit();
    }

    void setRoundNumber(int roundNumber) {
        viewPager.setCurrentItem(roundNumber - 1);
    }

    public void displayTournamentJoinedMessage() {
        Toast.makeText(getContext(), "Tournament joined.", Toast.LENGTH_SHORT).show();
    }

    public void displayTournamentStartedMessage() {
        Toast.makeText(getContext(), "Tournament started.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
