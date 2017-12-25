package edu.bracketly.frontend.app.main;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import edu.bracketly.frontend.app.BaseActivity;
import edu.bracketly.frontend.app.BaseActivityModule;
import edu.bracketly.frontend.app.tournament.list.TournamentFragmentModule;
import edu.bracketly.frontend.app.tournament.list.TournamentListFragment;
import edu.bracketly.frontend.dagger.ActivityScope;
import edu.bracketly.frontend.dagger.FragmentScope;

/**
 * Created by howor on 24.12.2017.
 */

@Module(includes = BaseActivityModule.class)
public abstract class MainActivityModule {

    @FragmentScope
    @ContributesAndroidInjector(modules = {TournamentFragmentModule.class})
    abstract TournamentListFragment tournamentListFragmentInjector();

    @Binds
    @ActivityScope
    abstract BaseActivity bindBaseActivity(MainActivity mainActivity);
}

