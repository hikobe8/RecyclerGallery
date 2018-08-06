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

    private final int mItemWidth;
    private int mDividerSize;
    private int mOrientation;

    public GalleryItemDecoration(Context context, int orientation, int itemWidth, int dividerSize) {
        super(context, orientation);
        mDividerSize = dividerSize;
        mItemWidth = itemWidth;
        mOrientation = orientation;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (mOrientation == HORIZONTAL) {
            int parentWidth = parent.getWidth();
            int position = parent.getChildAdapterPosition(view);
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            layoutParams.width = mItemWidth;
            view.setLayoutParams(layoutParams);
            if (position == 0) {
                outRect.set(Math.round((parentWidth - mItemWidth) / 2f), 0, Math.round(mDividerSize / 2f), 0);
            } else if (position == parent.getLayoutManager().getItemCount() - 1) {
                outRect.set(Math.round(mDividerSize / 2f), 0, Math.round((parentWidth - mItemWidth) / 2f), 0);
            } else {
                outRect.set(Math.round(mDividerSize / 2f), 0, Math.round(mDividerSize / 2f), 0);
            }
        }
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
    }
}
