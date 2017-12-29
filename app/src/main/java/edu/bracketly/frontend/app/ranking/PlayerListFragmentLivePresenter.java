package edu.bracketly.frontend.app.ranking;

import android.support.v7.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import edu.bracketly.frontend.api.PlayerApi;
import edu.bracketly.frontend.app.BasePresenter;
import edu.bracketly.frontend.dto.PlayerDto;
import edu.bracketly.frontend.utils.PaginationScrollListener;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by howor on 29.12.2017.
 */

public class PlayerListFragmentLivePresenter extends BasePresenter<PlayerListFragment>
        implements PlayerListPresenter {

    private PlayerApi playerApi;
    private int currentPageNumber;
    private boolean isLoading;
    private boolean isLastPage;
    private static final int pageSize = 10;
    private List<PlayerDto> players = new ArrayList<>();

    @Inject
    protected PlayerListFragmentLivePresenter(PlayerListFragment view,
                                              PlayerApi playerApi) {
        super(view);
        this.playerApi = playerApi;
    }

    @Override
    public void onResume() {
        resetState();
        if (view.list.getAdapter() == null) {
            setupAdapter();
        }
        loadNextPage();
    }

    private void resetState() {
        currentPageNumber = 0;
        isLastPage = false;
        isLoading = false;
        players.clear();
    }

    private void setupAdapter() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        view.list.setLayoutManager(linearLayoutManager);
        view.list.setAdapter(new PlayerRecyclerViewAdapter(this));
        view.list.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {
            @Override
            public void loadData() {
                loadNextPage();
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });
    }

    private void loadNextPage() {
        Disposable subscribe = playerApi.getAllPlayers(currentPageNumber, "rank", 10, "DESC")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(dtos -> {
                    if (dtos.size() < pageSize) {
                        isLastPage = true;
                    }
                    players.addAll(dtos);
                    view.list.getAdapter().notifyDataSetChanged();
                    isLoading = false;
                    view.setListEmpty(players.isEmpty());
                    currentPageNumber++;
                });
        disposable.add(subscribe);
    }

    @Override
    public PlayerDto getPlayer(int position) {
        return players.get(position);
    }

    @Override
    public int getPlayerCount() {
        return players != null ? players.size() : 0;
    }

    @Override
    public long getPlayerId(int position) {
        return players.get(position).getId();
    }

    @Override
    public String getOrdinal(int position) {
        return String.valueOf(position + 1);
    }
}
