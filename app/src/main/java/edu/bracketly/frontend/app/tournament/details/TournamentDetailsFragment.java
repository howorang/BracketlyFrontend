package edu.bracketly.frontend.app.tournament.details;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

    @BindView(R.id.pager)
    ViewPager viewPager;

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

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
