package in.nerd_is.recycler_simplification;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

/**
 * @author Xuqiang ZHENG on 18/4/20.
 */
public class EndlessScrollListener extends RecyclerView.OnScrollListener {

    private int visibleThreshold = 6;
    private RecyclerView.LayoutManager layoutManager;
    private LoadMoreSubject loadMoreSubject;

    public EndlessScrollListener(RecyclerView.LayoutManager layoutManager,
                                 LoadMoreSubject loadMoreSubject) {
        this.layoutManager = layoutManager;
        this.loadMoreSubject = loadMoreSubject;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        if (dy < 0 || loadMoreSubject.isLoading())
            return;

        int totalItemCount = layoutManager.getItemCount();
        int lastVisibleItemPosition = getLastVisibleItemPosition();

        if (lastVisibleItemPosition + visibleThreshold >= (totalItemCount - 1)) {
            loadMoreSubject.loadMore();
        }
    }

    public void attachTo(RecyclerView recyclerView) {
        recyclerView.addOnScrollListener(this);
    }

    public int getVisibleThreshold() {
        return visibleThreshold;
    }

    public void setVisibleThreshold(int visibleThreshold) {
        this.visibleThreshold = visibleThreshold;
    }

    private int getLastVisibleItemPosition() {
        int lastVisibleItemPosition = 0;
        if (layoutManager instanceof StaggeredGridLayoutManager) {
            int[] lastVisibleItemPositions = ((StaggeredGridLayoutManager) layoutManager)
                    .findLastVisibleItemPositions(null);
            lastVisibleItemPosition = getLastVisibleItem(lastVisibleItemPositions);
        } else if (layoutManager instanceof GridLayoutManager) {
            lastVisibleItemPosition = ((GridLayoutManager) layoutManager)
                    .findLastVisibleItemPosition();
        } else if (layoutManager instanceof LinearLayoutManager) {
            lastVisibleItemPosition = ((LinearLayoutManager) layoutManager)
                    .findLastVisibleItemPosition();
        }
        return lastVisibleItemPosition;
    }

    private int getLastVisibleItem(int[] lastVisibleItemPositions) {
        int max = lastVisibleItemPositions[0];
        for (int position : lastVisibleItemPositions) {
            if (position > max) {
                max = position;
            }
        }
        return max;
    }

    public interface LoadMoreSubject {
        boolean isLoading();
        void loadMore();
    }
}
