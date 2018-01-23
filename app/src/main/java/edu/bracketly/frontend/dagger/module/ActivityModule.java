package edu.bracketly.frontend.dagger.module;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import edu.bracketly.frontend.app.login.LoginActivity;
import edu.bracketly.frontend.app.login.LoginActivityModule;
import edu.bracketly.frontend.app.main.MainActivity;
import edu.bracketly.frontend.app.main.MainActivityModule;
import edu.bracketly.frontend.app.match.view.MatchActivity;
import edu.bracketly.frontend.app.match.view.MatchActivityModule;
import edu.bracketly.frontend.app.signup.SignUpActivity;
import edu.bracketly.frontend.app.signup.SignUpActivityModule;
import edu.bracketly.frontend.app.tournament.add.AddTournamentActivity;
import edu.bracketly.frontend.app.tournament.add.AddTournamentActivityModule;
import edu.bracketly.frontend.app.tournament.details.TournamentDetailsActivity;
import edu.bracketly.frontend.app.tournament.details.TournamentDetailsActivityModule;
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

    @ActivityScope
    @ContributesAndroidInjector(modules = {TournamentDetailsActivityModule.class})
    abstract TournamentDetailsActivity tournamentDetailsActivityInjector();

    @ActivityScope
    @ContributesAndroidInjector(modules = {MatchActivityModule.class})
    abstract MatchActivity matchActivityInjector();

    @ActivityScope
    @ContributesAndroidInjector(modules = {SignUpActivityModule.class})
    abstract SignUpActivity signUpActivityInjector();
}
