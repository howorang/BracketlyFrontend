package edu.bracketly.frontend.app.player;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import edu.bracketly.frontend.R;
import edu.bracketly.frontend.app.BaseFragment;
import edu.bracketly.frontend.dto.PlayerDto;

public class PlayerFragment extends BaseFragment<PlayerPresenter> {

    @BindView(R.id.username)
    TextView username;

    @BindView(R.id.rank)
    TextView rank;

    @BindView(R.id.matches_played)
    TextView matchesPlayed;

    private static final String ARG_PLAYER = "player";
    private PlayerDto playerDto;
    private Unbinder unbinder;

    public PlayerFragment() {
        // Required empty public constructor
    }

    public static PlayerFragment newInstance(PlayerDto playerDto) {
        PlayerFragment fragment = new PlayerFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PLAYER, playerDto);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            playerDto = (PlayerDto) getArguments().getSerializable(ARG_PLAYER);
            presenter.setPlayer(playerDto);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_player, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }
    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
