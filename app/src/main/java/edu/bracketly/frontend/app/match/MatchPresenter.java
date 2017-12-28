package edu.bracketly.frontend.app.match;

import javax.inject.Inject;

import edu.bracketly.frontend.app.BasePresenter;
import edu.bracketly.frontend.app.player.PlayerFragment;
import edu.bracketly.frontend.dto.MatchDto;

/**
 * Created by howor on 28.12.2017.
 */

public class MatchPresenter extends BasePresenter<MatchActivityFragment> {
    private long matchId;
    private long bracketId;
    private MatchDto matchDto;

    @Inject
    protected MatchPresenter(MatchActivityFragment view) {
        super(view);
    }

    @Override
    public void onResume() {
        if (matchDto == null) {
            //LOAD DTO
        }
        view.replaceFragment(view.playerOneFragmentContainer.getId(),
                PlayerFragment.newInstance(matchDto.getPlayers().get(0).getPlayer()));

        view.replaceFragment(view.playerTwoFragmentContainer.getId(),
                PlayerFragment.newInstance(matchDto.getPlayers().get(1).getPlayer()));
    }

    public void setMatchId(long matchId) {
        this.matchId = matchId;
    }

    public void setBracketId(long bracketId) {
        this.bracketId = bracketId;
    }

    public void setMatchDto(MatchDto matchDto) {
        this.matchDto = matchDto;
    }
}
