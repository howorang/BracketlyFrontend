package edu.bracketly.frontend.app.ranking;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.bracketly.frontend.R;

/**
 * Created by howor on 28.12.2017.
 */

public class PlayerRowViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.username)
    TextView username;

    @BindView(R.id.ranking_ordinal_label)
    TextView rankOrdinal;

    @BindView(R.id.rank)
    TextView rank;

    public PlayerRowViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

}
