package edu.bracketly.frontend.app.tournament.list;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import edu.bracketly.frontend.R;
import edu.bracketly.frontend.dto.TournamentSimpleDto;

/**
 * Created by howor on 24.12.2017.
 */

public class TournamentViewHolder extends RecyclerView.ViewHolder {

    private static final SimpleDateFormat hourFormat = new SimpleDateFormat("HH::mm", Locale.getDefault());
    private static final SimpleDateFormat dayFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());

    private Unbinder unbinder;

    @BindView(R.id.name)
    TextView name;

    @BindView(R.id.event_hour)
    TextView eventHour;

    @BindView(R.id.event_day)
    TextView eventDay;

    public TournamentViewHolder(View itemView) {
        super(itemView);
        unbinder = ButterKnife.bind(this, itemView);
    }

    public void bind(TournamentSimpleDto dto) {
        name.setText(dto.getName());
        eventDay.setText(dayFormat.format(dto.getEventDate()));
        eventHour.setText(hourFormat.format(dto.getEventDate()));
    }

    public void unbind() {
        unbinder.unbind();
    }
}
