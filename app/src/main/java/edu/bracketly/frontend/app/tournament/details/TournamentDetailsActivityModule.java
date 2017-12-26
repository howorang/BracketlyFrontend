package edu.bracketly.frontend.app.tournament.details;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import edu.bracketly.frontend.app.BaseActivity;
import edu.bracketly.frontend.app.BaseActivityModule;
import edu.bracketly.frontend.dagger.ActivityScope;
import edu.bracketly.frontend.dagger.FragmentScope;

/**
 * Created by howor on 25.12.2017.
 */

@Module(includes = BaseActivityModule.class)
public abstract class TournamentDetailsActivityModule {

    @FragmentScope
    @ContributesAndroidInjector(modules = {TournamentDetailsFragmentModule.class})
    abstract TournamentDetailsFragment tournamentDetailsFragmentInjector();

    @Binds
    @ActivityScope
    abstract BaseActivity bindBaseActivity(TournamentDetailsActivity tournamentDetailsActivity);

}
