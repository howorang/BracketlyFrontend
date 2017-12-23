package edu.bracketly.frontend.app.login;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import edu.bracketly.frontend.dagger.FragmentScope;

/**
 * Created by howor on 23.12.2017.
 */

@Module
public abstract class LoginActivityModule {

    @FragmentScope
    @ContributesAndroidInjector(modules = {LoginFragmentModule.class})
    abstract LoginActivityFragment loginActivityFragmentInjector();
}
