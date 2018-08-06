package com.ray.lib;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

/**
 * Author : hikobe8@github.com
 * Time : 2018/8/4 下午11:12
 * Description :
 */
public class GalleryScrollListener extends RecyclerView.OnScrollListener{

    private int mItemWidth;
    private int mTotalScrollX;

    public GalleryScrollListener(int itemWidth, int dividerSize) {
        mItemWidth = itemWidth + dividerSize;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        mTotalScrollX += dx;
        int position = mTotalScrollX / mItemWidth;
        scaleView(recyclerView, position);
        int offset = mTotalScrollX - mItemWidth * position;
        float percent = (float) Math.max(Math.abs(offset) * 1.0f / mItemWidth, 0.0001);
        Log.e("fuck", "percent = " + percent + " | position = " + position);
        int leftPosition = position - 1;
        int rightPosition = position + 1;
        View currentView = recyclerView.getLayoutManager().findViewByPosition(position);
        if (currentView != null) {
            currentView.setScaleY(1f - 0.1f*percent);
            currentView.setScaleX(1f - 0.1f*percent);
        }
        View leftView = recyclerView.getLayoutManager().findViewByPosition(leftPosition);

        if (leftView != null) {
            leftView.setScaleY(0.9f + 0.1f*percent);
            leftView.setScaleX(0.9f + 0.1f*percent);
        }
        View rightView = recyclerView.getLayoutManager().findViewByPosition(rightPosition);
        if (rightView != null) {
            rightView.setScaleY(0.9f + 0.1f*percent);
            rightView.setScaleX(0.9f + 0.1f*percent);
        }
    }

    private void scaleView(RecyclerView recyclerView, int position) {
        View left = recyclerView.getLayoutManager().findViewByPosition(position - 2);
        View right = recyclerView.getLayoutManager().findViewByPosition(position + 2);
        if (left != null) {
            left.setScaleY(0.9f);
            left.setScaleX(0.9f);
        }
        if (right != null) {
            right.setScaleY(0.9f);
            right.setScaleX(0.9f);
        }
    }

}
