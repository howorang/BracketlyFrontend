package edu.bracketly.frontend.app.main;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;

import butterknife.BindView;
import edu.bracketly.frontend.R;
import edu.bracketly.frontend.app.BaseActivity;
import edu.bracketly.frontend.app.ranking.PlayerListFragment;
import edu.bracketly.frontend.app.start.StartFragment;
import edu.bracketly.frontend.app.tournament.list.TournamentListFragment;

public class MainActivity extends BaseActivity {

    private static final String CHOSEN_FRAGMENT = "chosenFragment";

    @BindView(R.id.navigation)
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.navigation_ranking:
                    replaceFragment(R.id.fragment_container, PlayerListFragment.newInstance());
                    return true;

                case R.id.navigation_start:
                    replaceFragment(R.id.fragment_container, StartFragment.newInstance());
                    return true;

                case R.id.navigation_tournaments:
                    replaceFragment(R.id.fragment_container, TournamentListFragment.newInstance());
                    return true;

                default:
                    return false;
            }
        });
        if (savedInstanceState != null && savedInstanceState.containsKey(CHOSEN_FRAGMENT)) {
            bottomNavigationView.setSelectedItemId(savedInstanceState.getInt(CHOSEN_FRAGMENT));
        } else {
            bottomNavigationView.setSelectedItemId(R.id.navigation_start);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(CHOSEN_FRAGMENT, bottomNavigationView.getSelectedItemId());
    }
}
