package edu.bracketly.frontend.app.start;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import edu.bracketly.frontend.app.match.list.MatchListPresenter;
import edu.bracketly.frontend.app.player.PlayerFragment;
import edu.bracketly.frontend.app.player.PlayerFragmentModule;
import edu.bracketly.frontend.dagger.FragmentScope;
import edu.bracketly.frontend.dagger.InnerFragmentScope;

/**
 * Created by Piotr Borczyk on 02.01.2018.
 */

@Module
public abstract class StartFragmentModule {

    @Binds
    @FragmentScope
    abstract MatchListPresenter bindMatchListPresenter(StartFragmentPresenter presenter);

    @InnerFragmentScope
    @ContributesAndroidInjector(modules = {PlayerFragmentModule.class})
    abstract PlayerFragment playerFragmentInjector();
}
