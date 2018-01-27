package edu.bracketly.frontend.app.match.view;

import javax.inject.Inject;

import edu.bracketly.frontend.R;
import edu.bracketly.frontend.api.BracketApi;
import edu.bracketly.frontend.app.BasePresenter;
import edu.bracketly.frontend.app.player.PlayerFragment;
import edu.bracketly.frontend.consts.MATCH_STATUS;
import edu.bracketly.frontend.dto.MatchDto;
import edu.bracketly.frontend.dto.PlayerDto;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by howor on 28.12.2017.
 */

public class MatchPresenter extends BasePresenter<MatchActivityFragment> {
    private long matchId;
    private long bracketId;
    private MatchDto matchDto;
    private BracketApi bracketApi;
    private boolean isEditable;

    @Inject
    protected MatchPresenter(MatchActivityFragment view, BracketApi bracketApi) {
        super(view);
        this.bracketApi = bracketApi;
    }

    @Override
    public void onResume() {
        view.playerOneWinButton.setOnClickListener(view -> onPlayerWin(1));
        view.playerTwoWinButton.setOnClickListener(view -> onPlayerWin(2));
        if (matchDto == null) {
            //LOAD DTO
        }
        view.isEditable(isEditable);
        updateGui(matchDto.getMatchStatus());
        view.replaceFragment(view.playerOneFragmentContainer.getId(),
                PlayerFragment.newInstance(matchDto.getSeats().get(0).getPlayer()));

        view.replaceFragment(view.playerTwoFragmentContainer.getId(),
                PlayerFragment.newInstance(matchDto.getSeats().get(1).getPlayer()));
    }

    private void updateGui(MATCH_STATUS matchStatus) {
        if (isEditable) {
            switch (matchStatus) {
                case NOT_PLAYED:

                    break;

                case LIVE:
                    view.markLive();
                    break;

                case PLAYED:
                    view.matchPlayed();
                    break;
            }
        }
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

    public void setEditable(boolean editable) {
        isEditable = editable;
    }

    public void onFabClick() {
        if (matchDto.getMatchStatus() == MATCH_STATUS.NOT_PLAYED) {
            startMatch();
        }
    }

    private void startMatch() {
        Disposable subscribe = bracketApi.startMatch(bracketId, matchDto.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                    view.displayMatchStartMessage();
                    updateGui(MATCH_STATUS.LIVE);
                }, this::handleError);
        disposable.add(subscribe);
    }


    public void onPlayerWin(int player) {
        Disposable subscribe = bracketApi.playMatch(bracketId, matchDto.getId(), matchDto.getSeats().get(player - 1).getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                    view.displayPlayedMessage();
                    updateGui(MATCH_STATUS.PLAYED);
                    view.close();
                }, this::handleError);
        disposable.add(subscribe);
    }

    public int getPlayerOneResultIconId() {
        return getResultIconResId(matchDto, 1);
    }

    public int getPlayerTwoResultIconId() {
        return getResultIconResId(matchDto, 2);
    }

    private int getResultIconResId(MatchDto matchDto, int playerNumber) {
        if (matchDto.getWinner() == null) return 0;
        if (matchDto.getSeats().get(playerNumber - 1).getPlayer() != null) {
            PlayerDto player = matchDto.getSeats().get(playerNumber - 1).getPlayer();
            if (player.equals(matchDto.getWinner())) {
                return R.drawable.ic_won;
            } else {
                return R.drawable.ic_lost;
            }
        }
        return 0;
    }
}
