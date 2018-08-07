package com.ray.lib;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

/**
 * Author : hikobe8@github.com
 * Time : 2018/8/4 下午11:12
 * Description :
 */
public class GalleryScrollListener extends RecyclerView.OnScrollListener {

    private int mItemSize;
    private int mTotalScrollDistance;
    private float mScaleFactor;

    public GalleryScrollListener(int itemSize, int dividerSize, float scaleFactor) {
        mItemSize = itemSize + dividerSize;
        mScaleFactor = scaleFactor;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        int orientation = layoutManager.getOrientation();
        if (orientation == LinearLayoutManager.HORIZONTAL) {
            mTotalScrollDistance += dx;
        } else {
            //vertical
            mTotalScrollDistance += dy;
        }
        int position = mTotalScrollDistance / mItemSize;
        scaleView(recyclerView, position);
        int offset = mTotalScrollDistance - mItemSize * position;
        float percent = (float) Math.max(Math.abs(offset) * 1.0f / mItemSize, 0.0001);
        int prePosition = position - 1;
        int nextPosition = position + 1;
        View currentView = layoutManager.findViewByPosition(position);
        if (currentView != null) {
            currentView.setScaleY(1f - (1 - mScaleFactor) * percent);
            currentView.setScaleX(1f - (1 - mScaleFactor) * percent);
        }
        View preView = layoutManager.findViewByPosition(prePosition);
        if (preView != null) {
            preView.setScaleX(mScaleFactor + (1 - mScaleFactor) * percent);
            preView.setScaleY(mScaleFactor + (1 - mScaleFactor) * percent);
        }
        View nextView = layoutManager.findViewByPosition(nextPosition);
        if (nextView != null) {
            nextView.setScaleX(mScaleFactor + (1 - mScaleFactor) * percent);
            nextView.setScaleY(mScaleFactor + (1 - mScaleFactor) * percent);
        }
    }

    private void scaleView(RecyclerView recyclerView, int position) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        View preView = layoutManager.findViewByPosition(position - 2);
        View nextView = layoutManager.findViewByPosition(position + 2);
        if (preView != null) {
            preView.setScaleX(mScaleFactor);
            preView.setScaleY(mScaleFactor);
        }
        if (nextView != null) {
            nextView.setScaleX(mScaleFactor);
            nextView.setScaleY(mScaleFactor);
        }
    }

}
