package edu.bracketly.frontend;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;
import edu.bracketly.frontend.dagger.DaggerAppComponent;

/**
 * Created by Piotr Borczyk on 20.12.2017.
 */

public class BracketlyApp extends DaggerApplication {
    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
       return DaggerAppComponent.builder().create(this);
    }
}
