package edu.bracketly.frontend.app.tournament.round;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import edu.bracketly.frontend.R;


public class RoundFragment extends Fragment {

    private RoundPresenter presenter;

    @BindView(R.id.list)
    RecyclerView list;

    @BindView(R.id.no_items_message)
    TextView noItemsMessage;

    private static final String ARG_BRACKET_ID = "bracket-id";
    private static final String ARG_ROUND_NUMBER = "round-number";
    private Unbinder unbinder;
    public RoundFragment() {
    }

    @SuppressWarnings("unused")
    public static RoundFragment newInstance(long bracketId, int roundNumber, RoundPresenter presenter) {
        RoundFragment fragment = new RoundFragment();
        fragment.presenter = presenter;
        Bundle args = new Bundle();
        args.putLong(ARG_BRACKET_ID, bracketId);
        args.putInt(ARG_ROUND_NUMBER, roundNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.setView(this);
        if (getArguments() != null) {
            presenter.bracketId = getArguments().getLong(ARG_BRACKET_ID, -11L);
            presenter.roundNumber = getArguments().getInt(ARG_ROUND_NUMBER, -11);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_match_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
