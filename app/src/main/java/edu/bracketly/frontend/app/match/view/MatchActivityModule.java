package edu.bracketly.frontend.app.match.view;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import edu.bracketly.frontend.app.BaseActivity;
import edu.bracketly.frontend.app.BaseActivityModule;
import edu.bracketly.frontend.dagger.ActivityScope;
import edu.bracketly.frontend.dagger.FragmentScope;

/**
 * Created by howor on 28.12.2017.
 */

@Module(includes = BaseActivityModule.class)
public abstract class MatchActivityModule {
    @FragmentScope
    @ContributesAndroidInjector(modules = {MatchActivityFragmentModule.class})
    abstract MatchActivityFragment matchActivityFragmentInjector();

    @Binds
    @ActivityScope
    abstract BaseActivity bindBaseActivity(MatchActivity matchActivity);
}
