package edu.bracketly.frontend.app.player;

import javax.inject.Inject;

import edu.bracketly.frontend.app.BasePresenter;
import edu.bracketly.frontend.dto.PlayerDto;

/**
 * Created by howor on 28.12.2017.
 */

public class PlayerPresenter extends BasePresenter<PlayerFragment> {

    private PlayerDto playerDto;

    @Inject
    protected PlayerPresenter(PlayerFragment view) {
        super(view);
    }

    @Override
    public void onResume() {
        view.username.setText(playerDto.getUsername());
        view.rank.setText(String.valueOf(playerDto.getRank()));
        view.matchesPlayed.setText(String.valueOf(0));
    }

    void setPlayer(PlayerDto player) {
        this.playerDto = player;
    }
}
