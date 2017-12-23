package edu.bracketly.frontend.dagger.module;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import edu.bracketly.frontend.MainActivity;
import edu.bracketly.frontend.app.login.LoginActivity;
import edu.bracketly.frontend.app.login.LoginActivityModule;
import edu.bracketly.frontend.dagger.ActivityScope;

/**
 * Created by Piotr Borczyk on 20.12.2017.
 */

@Module
public abstract class ActivityModule {

    @ActivityScope
    @ContributesAndroidInjector
    abstract MainActivity mainActivityInjector();

    @ActivityScope
    @ContributesAndroidInjector(modules = {LoginActivityModule.class})
    abstract LoginActivity loginActivityInjector();
}
