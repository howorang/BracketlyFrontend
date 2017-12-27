package edu.bracketly.frontend.app.tournament.round;

import java.util.List;

import javax.inject.Inject;

import edu.bracketly.frontend.api.BracketApi;
import edu.bracketly.frontend.api.SingleEliminationBracketApi;
import edu.bracketly.frontend.app.BasePresenter;
import edu.bracketly.frontend.dto.MatchDto;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by howor on 27.12.2017.
 */

public class RoundPresenter extends BasePresenter<RoundFragment> {

    private List<MatchDto> matchDtos;
    private BracketApi bracketApi;
    private SingleEliminationBracketApi singleEliminationBracketApi;
    long bracketId;
    int roundNumber;

    @Inject
    protected RoundPresenter(RoundFragment view, BracketApi bracketApi,
                             SingleEliminationBracketApi singleEliminationBracketApi) {
        super(view);
        this.bracketApi = bracketApi;
        this.singleEliminationBracketApi = singleEliminationBracketApi;
    }

    @Override
    public void onResume() {
        singleEliminationBracketApi.getRound(bracketId, roundNumber)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(roundDto -> matchDtos = roundDto.getMatches());
    }

    public int getMatchCount() {
        return matchDtos.size();
    }

    public String getPlayerTwoLabel(int position) {
        return matchDtos.get(position).getPlayers().get(0).getPlayer().getUsername();
    }

    public String getPlayerOneLabel(int position) {
        return matchDtos.get(position).getPlayers().get(1).getPlayer().getUsername();
    }
}
