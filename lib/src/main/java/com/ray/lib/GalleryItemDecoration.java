package com.ray.lib;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Author : hikobe8@github.com
 * Time : 2018/8/2 下午10:53
 * Description :
 */
public class GalleryItemDecoration extends DividerItemDecoration {

    private final int mItemSize;
    private int mDividerSize;
    private int mOrientation;

    public GalleryItemDecoration(Context context, int orientation, int itemSize, int dividerSize) {
        super(context, orientation);
        mDividerSize = dividerSize;
        mItemSize = itemSize;
        mOrientation = orientation;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int position = parent.getChildAdapterPosition(view);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (mOrientation == HORIZONTAL) {
            int parentWidth = parent.getWidth();
            layoutParams.width = mItemSize;
            view.setLayoutParams(layoutParams);
            if (position == 0) {
                outRect.set(Math.round((parentWidth - mItemSize) / 2f), 0, Math.round(mDividerSize / 2f), 0);
            } else if (position == parent.getLayoutManager().getItemCount() - 1) {
                outRect.set(Math.round(mDividerSize / 2f), 0, Math.round((parentWidth - mItemSize) / 2f), 0);
            } else {
                outRect.set(Math.round(mDividerSize / 2f), 0, Math.round(mDividerSize / 2f), 0);
            }
        } else {
            int parentHeight = parent.getHeight();
            layoutParams.height = mItemSize;
            view.setLayoutParams(layoutParams);
            if (position == 0) {
                outRect.set(0, Math.round((parentHeight - mItemSize) / 2f), 0, Math.round(mDividerSize / 2f));
            } else if (position == parent.getLayoutManager().getItemCount() - 1) {
                outRect.set(0, Math.round(mDividerSize / 2f), 0, Math.round(mDividerSize / 2f));
            } else {
                outRect.set(0, Math.round(mDividerSize / 2f), 0, Math.round(mDividerSize / 2f));
            }
        }
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
    }
}
