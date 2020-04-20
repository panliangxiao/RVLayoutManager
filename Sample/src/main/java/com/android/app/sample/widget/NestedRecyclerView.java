package com.android.app.sample.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.NestedScrollingParent2;
import android.support.v4.view.NestedScrollingParentHelper;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class NestedRecyclerView extends RecyclerView implements NestedScrollingParent2 {

    private static final String TAG = NestedRecyclerView.class.getSimpleName();

    private NestedScrollingParentHelper mParentHelper;

    public NestedRecyclerView(@NonNull Context context) {
        super(context);
        init();
    }

    public NestedRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public NestedRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        mParentHelper = new NestedScrollingParentHelper(this);
    }

    @Override
    public boolean onStartNestedScroll(@NonNull View view, @NonNull View target, int axes, int type) {
        Log.d(TAG, "onStartNestedScroll axes = " + axes);
        return (axes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    @Override
    public void onNestedScrollAccepted(@NonNull View child, @NonNull View target, int axes, int type) {
        Log.d(TAG, "onNestedScrollAccepted: ");
        mParentHelper.onNestedScrollAccepted(child, target, axes, type);
        startNestedScroll(ViewCompat.SCROLL_AXIS_VERTICAL, type);
    }

    @Override
    public void onStopNestedScroll(@NonNull View target, int type) {
        Log.d(TAG, "onStopNestedScroll: ");
        mParentHelper.onStopNestedScroll(target, type);
        stopNestedScroll(type);
    }

    @Override
    public void onStopNestedScroll(@NonNull View target) {
        onStopNestedScroll(target, ViewCompat.TYPE_TOUCH);
    }

    @Override
    public void onNestedScroll(@NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {
        Log.d(TAG, "onStartNestedScroll");
    }

    @Override
    public void onNestedPreScroll(@NonNull View target, int dx, int dy, @Nullable int[] consumed, int type) {
        int offset = Integer.MAX_VALUE;
        if (target instanceof RecyclerView) {

            if (((RecyclerView) target).getLayoutManager() instanceof LinearLayoutManager) {
                LinearLayoutManager layoutManager = (LinearLayoutManager) ((RecyclerView) target).getLayoutManager();
                if (dy < 0) {
                    offset = layoutManager.findFirstCompletelyVisibleItemPosition();
                } else {
                    offset = layoutManager.findLastCompletelyVisibleItemPosition();
                }
            } else if (((RecyclerView) target).getLayoutManager() instanceof GridLayoutManager) {
                GridLayoutManager layoutManager = (GridLayoutManager) ((RecyclerView) target).getLayoutManager();
                if (dy < 0) {
                    offset = layoutManager.findFirstCompletelyVisibleItemPosition();
                } else {
                    offset = layoutManager.findLastCompletelyVisibleItemPosition();
                }
            } else if (((RecyclerView) target).getLayoutManager() instanceof StaggeredGridLayoutManager) {
                StaggeredGridLayoutManager layoutManager = (StaggeredGridLayoutManager) ((RecyclerView) target).getLayoutManager();
                if (dy < 0) {
                    offset = layoutManager.findFirstCompletelyVisibleItemPositions(null)[0];
                } else {
                    offset = layoutManager.findFirstCompletelyVisibleItemPositions(null)[layoutManager.getSpanCount() - 1];
                }
            }
            if (offset == 0 || offset == ((RecyclerView) target).getLayoutManager().getItemCount() -1 ){
                scrollBy(0, dy);
                if (consumed != null) {
                    consumed[1] = dy;
                }
            }
        }
        Log.d(TAG, "onNestedPreScroll:::dy::" + dy + ",,consumed[1]::" + (consumed != null ? consumed[1] : ""));

    }

    @Override
    public void onNestedScroll(@NonNull View target, int dxConsumed, int dyConsumed,
                               int dxUnconsumed, int dyUnconsumed) {
        onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, ViewCompat.TYPE_TOUCH);
    }

    @Override
    public void onNestedPreScroll(@NonNull View target, int dx, int dy, @NonNull int[] consumed) {
        onNestedPreScroll(target, dx, dy, consumed, ViewCompat.TYPE_TOUCH);
    }

    @Override
    public boolean onNestedFling(@NonNull View target, float velocityX, float velocityY,
                                 boolean consumed) {
        return false;
    }

    @Override
    public boolean onNestedPreFling(@NonNull View target, float velocityX, float velocityY) {
        return false;
    }

    @Override
    public int getNestedScrollAxes() {
        return mParentHelper.getNestedScrollAxes();
    }

}
