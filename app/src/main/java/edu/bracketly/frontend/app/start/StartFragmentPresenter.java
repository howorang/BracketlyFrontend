package edu.bracketly.frontend.app.start;

import java.util.List;

import javax.inject.Inject;

import edu.bracketly.frontend.api.PlayerApi;
import edu.bracketly.frontend.app.BasePresenter;
import edu.bracketly.frontend.app.UserContextHelper;
import edu.bracketly.frontend.app.match.list.MatchListPresenter;
import edu.bracketly.frontend.app.match.list.MyMatchRecyclerViewAdapter;
import edu.bracketly.frontend.dto.MatchDto;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Piotr Borczyk on 02.01.2018.
 */

public class StartFragmentPresenter extends BasePresenter<StartFragment>
        implements MatchListPresenter {

    private PlayerApi playerApi;
    private UserContextHelper contextHelper;
    private List<MatchDto> matches;

    @Inject
    protected StartFragmentPresenter(StartFragment view,
                                     PlayerApi playerApi,
                                     UserContextHelper contextHelper) {
        super(view);
        this.playerApi = playerApi;
        this.contextHelper = contextHelper;
    }

    @Override
    public void onResume() {
        setUpAdapter();
        playerApi.getPlayerDetails(contextHelper.getCurrentUser().getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((matchDtos) -> {
                    if (view == null) return;
                    matches = matchDtos.getLiveMatches();
                    view.notifyDataSetChanged();
                });
    }

    private void setUpAdapter() {
        MyMatchRecyclerViewAdapter adapter = new MyMatchRecyclerViewAdapter(this);
        view.list.setAdapter(adapter);
    }

    @Override
    public int getMatchCount() {
        return matches != null ? matches.size() : 0;
    }

    @Override
    public void onMatchClick(int position) {

    }

    @Override
    public long getMatchId(int position) {
        return matches.get(position).getId();
    }

    public String getPlayerTwoLabel(int position) {
        return getPlayerLabel(matches.get(position), 2);
    }

    public String getPlayerOneLabel(int position) {
        return getPlayerLabel(matches.get(position), 1);
    }

    private String getPlayerLabel(MatchDto matchDto, int playerNumber) {
        if (matchDto.getSeats().get(playerNumber - 1).getPlayer() != null) {
            return matchDto.getSeats().get(playerNumber - 1).getPlayer().getUsername();
        } else {
            return "Unknown";
        }
    }
}
