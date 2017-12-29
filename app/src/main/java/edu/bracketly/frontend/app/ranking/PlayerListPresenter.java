package edu.bracketly.frontend.app.ranking;

import edu.bracketly.frontend.app.Presenter;
import edu.bracketly.frontend.dto.PlayerDto;

/**
 * Created by howor on 29.12.2017.
 */

public interface PlayerListPresenter extends Presenter {
    PlayerDto getPlayer(int position);

    int getPlayerCount();

    long getPlayerId(int position);
}
