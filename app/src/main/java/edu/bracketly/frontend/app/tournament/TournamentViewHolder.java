package edu.bracketly.frontend.app.tournament;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import edu.bracketly.frontend.R;
import edu.bracketly.frontend.dto.TournamentSimpleDto;

/**
 * Created by howor on 24.12.2017.
 */

public class TournamentViewHolder extends RecyclerView.ViewHolder {

    private Unbinder unbinder;

    @BindView(R.id.name)
    TextView name;

    @BindView(R.id.event_date)
    TextView eventDate;

    public TournamentViewHolder(View itemView) {
        super(itemView);
        unbinder = ButterKnife.bind(this.itemView);
    }

    public void bind(TournamentSimpleDto dto) {
        name.setText(dto.getName());
        eventDate.setText(dto.getEventDate().toString());
    }

    public void unbind() {
        unbinder.unbind();
    }
}
