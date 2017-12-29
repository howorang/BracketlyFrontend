package edu.bracketly.frontend.app.ranking;

import android.support.v7.widget.LinearLayoutManager;

import java.util.List;

import javax.inject.Inject;

import edu.bracketly.frontend.app.BasePresenter;
import edu.bracketly.frontend.dto.PlayerDto;

/**
 * Created by howor on 29.12.2017.
 */

public class PlayerListStaticPresenter extends BasePresenter<PlayerListFragment>
        implements PlayerListPresenter {

    private List<PlayerDto> players;

    @Inject
    protected PlayerListStaticPresenter(PlayerListFragment view) {
        super(view);
    }

    @Override
    public void onResume() {
        if (view.list.getAdapter() == null) {
            setupAdapter();
        }
    }

    private void setupAdapter() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        view.list.setLayoutManager(linearLayoutManager);
        view.list.setAdapter(new PlayerRecyclerViewAdapter(this));
    }

    @Override
    public PlayerDto getPlayer(int position) {
        return players.get(position);
    }

    @Override
    public int getPlayerCount() {
        return players != null ? players.size() : 0;
    }

    @Override
    public long getPlayerId(int position) {
        return players.get(position).getId();
    }

    public void setData(List<PlayerDto> data) {
        this.players = data;
        view.list.getAdapter().notifyDataSetChanged();
    }
}
