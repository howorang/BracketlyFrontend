package edu.bracketly.frontend.utils;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by howor on 24.12.2017.
 */

public abstract class PaginationScrollListener extends RecyclerView.OnScrollListener {

    private LinearLayoutManager layoutManager;

    public PaginationScrollListener(LinearLayoutManager layoutManager) {
        this.layoutManager = layoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        int visibleItemCount = layoutManager.getChildCount();
        int totalItemCount = layoutManager.getItemCount();
        int firstVisableItemPosition = layoutManager.findFirstVisibleItemPosition();

        if (!isLastPage() && !isLoading()) {
            if (firstVisableItemPosition + visibleItemCount >= totalItemCount
                    && firstVisableItemPosition >= 0) {
                loadData();
            }
        }
    }

    public abstract void loadData();

    public abstract boolean isLastPage();

    public abstract boolean isLoading();
}
