package edu.bracketly.frontend.app.tournament.details;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import edu.bracketly.frontend.R;
import edu.bracketly.frontend.app.BaseFragment;


public class TournamentDetailsFragment extends BaseFragment<TournamentDetailsPresenter> {

    public interface Host {
        CollapsingToolbarLayout getToolbarLayout();

        TextView getEventDayText();

        TextView getEventHourText();
    }

    public static final String TOURNAMENT_ID = "tournament_id";

    CollapsingToolbarLayout toolbarLayout;
    TextView eventDay;
    TextView eventHour;

    @BindView(R.id.fragment_container)
    FrameLayout fragmentContainer;

    @BindView(R.id.pager)
    ViewPager viewPager;

    @BindView(R.id.no_items_message)
    TextView noItemsMessage;

    @BindView(R.id.modify_button)
    Button modifyButton;

    @BindView(R.id.start_button)
    Button startButton;

    @BindView(R.id.join_button)
    Button joinButton;

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
        presenter.setTournamentId(tournamentId);
    }

    @OnClick(R.id.join_button)
    public void onJoinClick(View view) {
        presenter.onJoinButtonClick();
    }

    @OnClick(R.id.modify_button)
    public void onModifyClick(View view) {
        presenter.onModifyButtonClick();
    }

    @OnClick(R.id.start_button)
    public void onStartClick(View view) {
        presenter.onStartButtonClick();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tournament_detail, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
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

    public void setModifyButtonVisibility(boolean isVisible) {
        modifyButton.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }

    public void setJoinButtonVisibility(boolean isVisible) {
        joinButton.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }

    public void setStartButtonVisibility(boolean isVisible) {
        startButton.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }

    public void isTournamentButtonPanelVisible(boolean isVisible) {
        Button[] buttons = {joinButton, startButton, modifyButton};
        for (Button button : buttons) {
            button.setVisibility(isVisible ? View.VISIBLE : View.GONE);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
