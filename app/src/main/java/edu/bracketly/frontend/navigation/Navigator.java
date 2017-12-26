package edu.bracketly.frontend.navigation;

import android.content.Context;
import android.content.Intent;

import edu.bracketly.frontend.app.main.MainActivity;
import edu.bracketly.frontend.app.tournament.add.AddTournamentActivity;
import edu.bracketly.frontend.app.tournament.details.TournamentDetailsActivity;
import edu.bracketly.frontend.app.tournament.details.TournamentDetailsFragment;

/**
 * Created by howor on 23.12.2017.
 */

public final class Navigator {
    private Navigator() {
    }

    public static void openMainActivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    public static void openAddTournamentActivity(Context context) {
        Intent intent = new Intent(context, AddTournamentActivity.class);
        context.startActivity(intent);
    }

    public static void openTournamentDetailsActivity(Context context, long tournamentId) {
        Intent intent = new Intent(context, TournamentDetailsActivity.class);
        intent.putExtra(TournamentDetailsFragment.TOURNAMENT_ID, tournamentId);
        context.startActivity(intent);
    }
}
