package edu.bracketly.frontend.app.tournament.list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.bracketly.frontend.R;
import edu.bracketly.frontend.dto.TournamentSimpleDto;

public class TournamentRecyclerViewAdapter extends RecyclerView.Adapter<TournamentViewHolder> {

    private TournamentPresenter presenter;

    public TournamentRecyclerViewAdapter(TournamentPresenter presenter) {
        this.presenter = presenter;
        setHasStableIds(true);
    }

    @Override
    public TournamentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_tournament_row, parent, false);
        return new TournamentViewHolder(v);

    }

    @Override
    public void onBindViewHolder(TournamentViewHolder holder, int position) {
        TournamentSimpleDto dto = presenter.getItem(position);
        holder.bind(dto);
        holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onTournamentRowClick(position);
            }
        });
    }

    @Override
    public void onViewRecycled(TournamentViewHolder holder) {
        super.onViewRecycled(holder);
        holder.setOnClickListener(null);
    }

    @Override
    public long getItemId(int position) {
        return presenter.getId(position);
    }

    @Override
    public int getItemCount() {
        return presenter.getItemCount();
    }
}
