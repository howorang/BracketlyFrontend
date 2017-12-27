package edu.bracketly.frontend.app.tournament.round;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.bracketly.frontend.R;

public class MyMatchRecyclerViewAdapter extends RecyclerView.Adapter<MatchViewHolder> {

    private RoundPresenter presenter;

    public MyMatchRecyclerViewAdapter(RoundPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public MatchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_round, parent, false);
        return new MatchViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MatchViewHolder holder, int position) {
        holder.playerOneLabel.setText(presenter.getPlayerOneLabel(position));
        holder.playerTwpLabel.setText(presenter.getPlayerTwoLabel(position));
    }


    @Override
    public int getItemCount() {
        return presenter.getMatchCount();
    }

}
