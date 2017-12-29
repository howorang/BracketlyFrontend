package edu.bracketly.frontend.app.ranking;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import edu.bracketly.frontend.R;
import edu.bracketly.frontend.app.BaseFragment;


public class PlayerListFragment extends BaseFragment<PlayerListPresenter> {

    @BindView(R.id.list)
    RecyclerView list;

    @BindView(R.id.no_items_message)
    FrameLayout noItemsMessage;

    private Unbinder unbinder;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public PlayerListFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static PlayerListFragment newInstance(int columnCount) {
        PlayerListFragment fragment = new PlayerListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_player_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    public void setListEmpty(boolean isEmpty) {
        if (isEmpty) {
            list.setVisibility(View.GONE);
            noItemsMessage.setVisibility(View.VISIBLE);
        } else {
            list.setVisibility(View.VISIBLE);
            noItemsMessage.setVisibility(View.GONE);
        }
    }
}
