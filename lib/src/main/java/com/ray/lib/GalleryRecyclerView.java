package com.ray.lib;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ViewTreeObserver;

/**
 * Author : hikobe8@github.com
 * Time : 2018/8/4 下午11:22
 * Description :
 */
public class GalleryRecyclerView extends RecyclerView {

    private final float DEFAULT_ITEM_SCALE_FACTOR = 0.9f;
    private final float DEFAULT_ITEM_SHOW_RATIO = 0.1f; //两边展示的区域大小
    private int mDividerSize;
    private int mOrientation = LinearLayoutManager.HORIZONTAL;
    private float mScaleFactor = DEFAULT_ITEM_SCALE_FACTOR;
    private float mItemShowRatio = DEFAULT_ITEM_SHOW_RATIO;

    public GalleryRecyclerView(Context context) {
        this(context, null, 0);
    }

    public GalleryRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
        int DEFAULT_DIVIDER_SIZE_IN_DP = 8;
        mDividerSize = Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, DEFAULT_DIVIDER_SIZE_IN_DP, getResources().getDisplayMetrics()));
        new LinearSnapHelper().attachToRecyclerView(this);
        setLayoutManager(new LinearLayoutManager(getContext(), mOrientation, false));
    }

    public void setDividerSize(int dividerSize) {
        mDividerSize = dividerSize;
    }

    public void setOrientation(int orientation) {
        mOrientation = orientation;
        setLayoutManager(new LinearLayoutManager(getContext(), mOrientation, false));
    }

    public void setScaleFactor(float scaleFactor) {
        mScaleFactor = scaleFactor;
    }

    public void setItemShowRatio(float itemShowRatio) {
        mItemShowRatio = itemShowRatio;
    }

    public GalleryRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setupGallery();
    }

    private void setupGallery() {
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (getWidth() != 0 && getHeight() != 0) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    } else {
                        getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    }
                    int itemSize = 0;
                    if (mOrientation == LinearLayoutManager.HORIZONTAL) {
                        itemSize = Math.round((getWidth() - 2 * mDividerSize) / (1 + 2 * mItemShowRatio * mScaleFactor));
                    } else if (mOrientation == LinearLayoutManager.VERTICAL) {
                        itemSize = Math.round((getHeight() - 2 * mDividerSize) / (1 + 2 * mItemShowRatio * mScaleFactor));
                    }
                    mDividerSize = Math.round(mDividerSize - itemSize * (1 - mScaleFactor)/2f);
                    addOnScrollListener(new GalleryScrollListener(itemSize, mDividerSize, mScaleFactor));
                    addItemDecoration(new GalleryItemDecoration(getContext(), mOrientation, itemSize, mDividerSize));
                }
            }

        });
    }

}
