package edu.bracketly.frontend.app.match;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import edu.bracketly.frontend.R;
import edu.bracketly.frontend.app.BaseFragment;
import edu.bracketly.frontend.dto.MatchDto;


/**
 * A placeholder fragment containing a simple view.
 */
public class MatchActivityFragment extends BaseFragment<MatchPresenter> {

    interface HostContract {
        FloatingActionButton getFab();
    }

    public static final String ARG_BRACKET_ID = "bracket_id";
    public static final String ARG_MATCH_ID = "match_id";
    public static final String ARG_MATCH_DTO = "match_dto";

    @BindView(R.id.player_one_fragment_container)
    FrameLayout playerOneFragmentContainer;

    @BindView(R.id.player_two_fragment_container)
    FrameLayout playerTwoFragmentContainer;

    private Unbinder unbinder;
    private HostContract host;

    public MatchActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_match, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean isDtoAvailiable = true;
        boolean areIdsAvailiable = true;
        if (getArguments() == null) {
            throw new RuntimeException("Bad params!");
        }
        if (!getArguments().containsKey(ARG_MATCH_ID) ||
                !getArguments().containsKey(ARG_BRACKET_ID)) {
            areIdsAvailiable = false;
        }

        if (!getArguments().containsKey(ARG_MATCH_DTO)) {
            isDtoAvailiable = false;
        }

        if (!areIdsAvailiable && !isDtoAvailiable) {
            throw new RuntimeException("Bad params!");
        }
        long bracketId = getArguments().getLong(ARG_BRACKET_ID, -11L);
        long matchId = getArguments().getLong(ARG_MATCH_ID, -11L);
        MatchDto matchDto = (MatchDto) getArguments().getSerializable(ARG_MATCH_DTO);
        presenter.setBracketId(bracketId);
        presenter.setMatchId(matchId);
        presenter.setMatchDto(matchDto);
        host.getFab().setOnClickListener(view -> presenter.onFabClick());
    }

    void replaceFragment(@IdRes int containerId, Fragment fragment) {
        getChildFragmentManager().beginTransaction().replace(containerId, fragment).commit();
    }

    public void displayMatchStartMessage() {
        Toast.makeText(getContext(), "Tournament started.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        host = (HostContract) context;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
