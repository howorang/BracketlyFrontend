package edu.bracketly.frontend.dagger.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import edu.bracketly.frontend.app.UserContextHelper;

/**
 * Created by howor on 30.12.2017.
 */

@Module
public abstract class ContextModule {
    @Provides
    @Singleton
    static UserContextHelper provideUserContextHelper() {
        return new UserContextHelper();
    }
}
