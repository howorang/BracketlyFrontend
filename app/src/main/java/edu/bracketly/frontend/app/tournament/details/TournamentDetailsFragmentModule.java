package edu.bracketly.frontend.app.tournament.details;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import edu.bracketly.frontend.app.tournament.round.RoundFragment;
import edu.bracketly.frontend.app.tournament.round.RoundFragmentModule;
import edu.bracketly.frontend.dagger.PageFragmentScope;

/**
 * Created by howor on 25.12.2017.
 */

@Module
public abstract class TournamentDetailsFragmentModule {

    @PageFragmentScope
    @ContributesAndroidInjector(modules = {RoundFragmentModule.class})
    abstract RoundFragment roundFragmentInjector();

}
