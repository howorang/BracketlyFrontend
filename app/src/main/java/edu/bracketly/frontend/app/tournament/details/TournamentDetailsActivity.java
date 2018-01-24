package edu.bracketly.frontend.app.tournament.details;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import butterknife.BindView;
import edu.bracketly.frontend.R;
import edu.bracketly.frontend.app.BaseActivity;


/**
 * An activity representing a single Item detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link }.
 */
public class TournamentDetailsActivity extends BaseActivity implements TournamentDetailsFragment.Host {

    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;

    @BindView(R.id.event_day)
    TextView eventDay;

    @BindView(R.id.event_hour)
    TextView eventHour;

    @BindView(R.id.event_organizer)
    TextView eventOrganizer;

    @BindView(R.id.event_status)
    TextView eventStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tournament_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putLong(TournamentDetailsFragment.TOURNAMENT_ID,
                    getIntent().getLongExtra(TournamentDetailsFragment.TOURNAMENT_ID, -11L));
            TournamentDetailsFragment fragment = new TournamentDetailsFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.item_detail_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                break;
        }
        return true;
    }

    @Override
    public CollapsingToolbarLayout getToolbarLayout() {
        return toolbarLayout;
    }

    @Override
    public TextView getEventDayText() {
        return eventDay;
    }

    @Override
    public TextView getEventHourText() {
        return eventHour;
    }

    @Override
    public TextView getEventOrganizerText() {
        return eventOrganizer;
    }

    @Override
    public TextView getEventStatusText() {
        return eventStatus;
    }
}
