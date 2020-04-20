package com.android.app.sample;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;


/**
 * Intercept touch event of RecyclerView binded
 */
public class SwipeItemTouchListener implements RecyclerView.OnItemTouchListener {

    private LinearLayoutManager layoutManager;

    public SwipeItemTouchListener(RecyclerView recyclerView) {
        if (recyclerView == null){
            throw new RuntimeException("recyclerView is null!!!");
        }
        this.layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
    }

    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
        recyclerView.requestDisallowInterceptTouchEvent(findScrollableChildViewUnder(motionEvent) != null);
        return false;
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean b) {

    }

    private View findScrollableChildViewUnder(MotionEvent event) {
        final int x = (int) event.getX();
        final int y = (int) event.getY();
        final int first = getLayoutManager().findFirstVisibleItemPosition();
        final int last = getLayoutManager().findLastVisibleItemPosition();
        for (int i = 0; i <= last - first; i++) {
            View child = getLayoutManager().getChildAt(i);
            if (child instanceof ViewGroup) {
                float translationX = child.getTranslationX();
                float translationY = child.getTranslationY();
                if (x >= (float) child.getLeft() + translationX
                        && x <= (float) child.getRight() + translationX
                        && y >= (float) child.getTop() + translationY
                        && y <= (float) child.getBottom() + translationY) {
                    if (findCanScrollView(child) != null) {
                        return child;
                    }
                }
            }
        }
        return null;
    }

    private View findCanScrollView(View v) {
        if (v instanceof ViewGroup) {
            ViewGroup target = (ViewGroup) v;
            if (target instanceof RecyclerView
                    && target.getVisibility() == View.VISIBLE) {
                return target;
            } else {
                for (int i = 0; i < target.getChildCount(); ++i) {
                    View view = findCanScrollView(target.getChildAt(i));
                    if (view != null) {
                        return view;
                    }
                }
                return null;
            }
        } else {
            return null;
        }
    }

    public LinearLayoutManager getLayoutManager() {
        return layoutManager;
    }
}