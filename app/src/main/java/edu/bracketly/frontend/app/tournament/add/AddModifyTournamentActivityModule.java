package edu.bracketly.frontend.app.tournament.add;

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
public abstract class AddModifyTournamentActivityModule {
    @FragmentScope
    @ContributesAndroidInjector(modules = {AddModifyTournamentFragmentModule.class})
    abstract AddModifyTournamentFragment addTournamentFragmentInjector();

    @Binds
    @ActivityScope
    abstract BaseActivity bindBaseActivity(AddModifyTournamentActivity tournamentActivity);
}
