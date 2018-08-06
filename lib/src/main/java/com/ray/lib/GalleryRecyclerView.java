package com.ray.lib;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
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

    public GalleryRecyclerView(Context context) {
        this(context, null, 0);
    }

    public GalleryRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GalleryRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (getWidth() != 0) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    } else {
                        getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    }
                    int dividerSize = Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics()));
                    int itemWidth = getWidth() - 4* dividerSize;
                    dividerSize = Math.round(dividerSize - itemWidth * 0.05f);
                    setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                    addOnScrollListener(new GalleryScrollListener(itemWidth, dividerSize));
                    addItemDecoration(new GalleryItemDecoration(getContext(), DividerItemDecoration.HORIZONTAL, itemWidth, dividerSize));
                    new LinearSnapHelper().attachToRecyclerView(GalleryRecyclerView.this);
                }
            }

        });
    }

}
