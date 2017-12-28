package edu.bracketly.frontend.app.match;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import edu.bracketly.frontend.app.player.PlayerFragment;
import edu.bracketly.frontend.app.player.PlayerFragmentModule;
import edu.bracketly.frontend.dagger.InnerFragmentScope;

/**
 * Created by howor on 28.12.2017.
 */

@Module
public abstract class MatchActivityFragmentModule {

    @InnerFragmentScope
    @ContributesAndroidInjector(modules = {PlayerFragmentModule.class})
    abstract PlayerFragment playerFragmentInjector();


}
