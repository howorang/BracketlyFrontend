package edu.bracketly.frontend.app.match;

import javax.inject.Inject;

import edu.bracketly.frontend.api.BracketApi;
import edu.bracketly.frontend.app.BasePresenter;
import edu.bracketly.frontend.app.player.PlayerFragment;
import edu.bracketly.frontend.dto.MatchDto;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by howor on 28.12.2017.
 */

public class MatchPresenter extends BasePresenter<MatchActivityFragment> {
    private long matchId;
    private long bracketId;
    private MatchDto matchDto;
    private BracketApi bracketApi;

    @Inject
    protected MatchPresenter(MatchActivityFragment view, BracketApi bracketApi) {
        super(view);
        this.bracketApi = bracketApi;
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

    public void onFabClick() {
        bracketApi.startMatch(bracketId, matchId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(view::displayMatchStartMessage);
    }
}
