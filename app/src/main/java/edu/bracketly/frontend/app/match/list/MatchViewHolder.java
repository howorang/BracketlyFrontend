package edu.bracketly.frontend.app.match.list;

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

    private View thisView;

    public MatchViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        thisView = itemView;
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        thisView.setOnClickListener(onClickListener);
    }

    public void unbind() {
        thisView.setOnClickListener(null);
    }
}
