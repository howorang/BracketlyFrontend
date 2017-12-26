package edu.bracketly.frontend.app.tournament.details;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.ButterKnife;
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
