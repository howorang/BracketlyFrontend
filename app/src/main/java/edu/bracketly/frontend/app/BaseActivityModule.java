package edu.bracketly.frontend.app;

import android.app.FragmentManager;
import android.content.Context;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import edu.bracketly.frontend.dagger.ActivityScope;

/**
 * Created by howor on 24.12.2017.
 */

@Module
public abstract class BaseActivityModule {

    @Binds
    @ActivityScope
    abstract Context bindContext(BaseActivity activity);

    @ActivityScope
    @Provides
    static FragmentManager provideFragmentManager(BaseActivity activity) {
        return activity.getFragmentManager();
    }
}
