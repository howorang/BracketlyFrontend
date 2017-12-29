package edu.bracketly.frontend.app.ranking;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.bracketly.frontend.R;
import edu.bracketly.frontend.dto.PlayerDto;

public class PlayerRecyclerViewAdapter extends RecyclerView.Adapter<PlayerRowViewHolder> {

    private PlayerListPresenter presenter;

    public PlayerRecyclerViewAdapter(PlayerListPresenter presenter) {
        this.presenter = presenter;
        setHasStableIds(true);
    }

    @Override
    public PlayerRowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_tournament_row, parent, false);
        return new PlayerRowViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PlayerRowViewHolder holder, int position) {
        PlayerDto playerDto = presenter.getPlayer(position);
        holder.rank.setText(String.valueOf(playerDto.getRank()));
        holder.rankOrdinal.setText(String.valueOf(position));
        holder.username.setText(playerDto.getUsername());
    }

    @Override
    public int getItemCount() {
        return presenter.getPlayerCount();
    }

    @Override
    public long getItemId(int position) {
        return presenter.getPlayerId(position);
    }
}
