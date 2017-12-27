package edu.bracketly.frontend.app.tournament.round;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.bracketly.frontend.R;

/**
 * Created by howor on 27.12.2017.
 */

public class MatchViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.player_one_label)
    TextView playerOneLabel;

    @BindView(R.id.player_two_label)
    TextView playerTwpLabel;

    public MatchViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(true, itemView);
    }
}
