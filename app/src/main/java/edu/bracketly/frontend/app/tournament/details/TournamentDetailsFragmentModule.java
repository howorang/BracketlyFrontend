package edu.bracketly.frontend.app.tournament.details;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import edu.bracketly.frontend.app.ranking.PlayerListFragment;
import edu.bracketly.frontend.app.ranking.PlayerListFragmentModule;
import edu.bracketly.frontend.app.ranking.PlayerListPresenter;
import edu.bracketly.frontend.app.ranking.PlayerListStaticPresenter;
import edu.bracketly.frontend.dagger.InnerFragmentScope;

/**
 * Created by howor on 25.12.2017.
 */

@Module
public abstract class TournamentDetailsFragmentModule {

    @Binds
    @InnerFragmentScope
    abstract PlayerListPresenter bindPlayerListPresenter(PlayerListStaticPresenter presenter);

    @InnerFragmentScope
    @ContributesAndroidInjector(modules = {PlayerListFragmentModule.class})
    abstract PlayerListFragment playerListFragmentInjector();
}
