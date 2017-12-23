package edu.bracketly.frontend.dagger;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import dagger.android.support.DaggerApplication;
import edu.bracketly.frontend.dagger.module.ActivityModule;
import edu.bracketly.frontend.dagger.module.ApiModule;

/**
 * Created by Piotr Borczyk on 20.12.2017.
 */

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        ActivityModule.class,
        ApiModule.class
})
public interface AppComponent extends AndroidInjector<DaggerApplication> {
    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<DaggerApplication> {}

}
