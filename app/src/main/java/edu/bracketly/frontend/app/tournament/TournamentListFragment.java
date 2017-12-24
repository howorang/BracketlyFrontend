package edu.bracketly.frontend.app.tournament;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import edu.bracketly.frontend.R;
import edu.bracketly.frontend.app.BaseFragment;


public class TournamentListFragment extends BaseFragment<TournamentPresenter> {

    private Unbinder unbinder;

    @BindView(R.id.list)
    RecyclerView list;

    public TournamentListFragment() {
    }

    public static TournamentListFragment newInstance() {
        TournamentListFragment fragment = new TournamentListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tournament_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
