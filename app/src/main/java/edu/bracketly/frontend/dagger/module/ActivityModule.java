package edu.bracketly.frontend.dagger.module;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import edu.bracketly.frontend.app.login.LoginActivity;
import edu.bracketly.frontend.app.login.LoginActivityModule;
import edu.bracketly.frontend.app.main.MainActivity;
import edu.bracketly.frontend.app.main.MainActivityModule;
import edu.bracketly.frontend.app.tournament.add.AddTournamentActivity;
import edu.bracketly.frontend.app.tournament.add.AddTournamentActivityModule;
import edu.bracketly.frontend.dagger.ActivityScope;

/**
 * Created by Piotr Borczyk on 20.12.2017.
 */

@Module
public abstract class ActivityModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = {MainActivityModule.class})
    abstract MainActivity mainActivityInjector();

    @ActivityScope
    @ContributesAndroidInjector(modules = {LoginActivityModule.class})
    abstract LoginActivity loginActivityInjector();

    @ActivityScope
    @ContributesAndroidInjector(modules = {AddTournamentActivityModule.class})
    abstract AddTournamentActivity addTournamentActivityInjector();
}
