package edu.bracketly.frontend.app.tournament.add;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import edu.bracketly.frontend.R;
import edu.bracketly.frontend.app.BaseActivity;

public class AddTournamentActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tournament);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

}
