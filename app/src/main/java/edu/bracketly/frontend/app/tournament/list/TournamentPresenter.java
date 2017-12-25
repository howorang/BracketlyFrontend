package edu.bracketly.frontend.app.tournament.list;

import android.support.v7.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import edu.bracketly.frontend.api.TournamentApi;
import edu.bracketly.frontend.app.BasePresenter;
import edu.bracketly.frontend.dto.TournamentSimpleDto;
import edu.bracketly.frontend.utils.PaginationScrollListener;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by howor on 24.12.2017.
 */

public class TournamentPresenter extends BasePresenter<TournamentListFragment> {

    private int currentPageNumber = 0;
    private boolean isLoading;
    private boolean isLastPage;
    private TournamentApi tournamentApi;
    private List<TournamentSimpleDto> tournaments = new ArrayList<>();


    @Inject
    protected TournamentPresenter(TournamentListFragment view, TournamentApi tournamentApi) {
        super(view);
        this.tournamentApi = tournamentApi;
    }


    @Override
    public void onResume() {
        loadNextPage();
        setupAdapter();
    }

    private void setupAdapter() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        view.list.setLayoutManager(linearLayoutManager);
        view.list.setAdapter(new TournamentRecyclerViewAdapter(this));
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
        isLoading = true;
        Disposable disposableCall = tournamentApi.getAllTournaments(currentPageNumber, "name", 5, "DESC")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        tournamentSimpleDtos -> {
                            if (tournamentSimpleDtos.isEmpty()) {
                                isLastPage = true;
                                return;
                            }
                            tournaments.addAll(tournamentSimpleDtos);
                            view.list.getAdapter().notifyDataSetChanged();
                            isLoading = false;
                        });
        disposable.add(disposableCall);
        currentPageNumber++;
    }

    public int getItemCount() {
        return tournaments.size();
    }

    public TournamentSimpleDto getItem(int position) {
        return tournaments.get(position);
    }
}
