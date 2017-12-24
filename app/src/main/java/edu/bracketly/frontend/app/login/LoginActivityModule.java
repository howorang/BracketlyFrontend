package edu.bracketly.frontend.app.login;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import edu.bracketly.frontend.app.BaseActivity;
import edu.bracketly.frontend.app.BaseActivityModule;
import edu.bracketly.frontend.dagger.ActivityScope;
import edu.bracketly.frontend.dagger.FragmentScope;

/**
 * Created by howor on 23.12.2017.
 */

@Module(includes = BaseActivityModule.class)
public abstract class LoginActivityModule {

    @FragmentScope
    @ContributesAndroidInjector(modules = {LoginFragmentModule.class})
    abstract LoginActivityFragment loginActivityFragmentInjector();

    @Binds
    @ActivityScope
    abstract BaseActivity bindBaseActivity(LoginActivity loginActivity);
}
