package com.android.app.sample;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;


/**
 * Intercept touch event of RecyclerView binded
 */
public class SwipeItemTouchListener implements RecyclerView.OnItemTouchListener {

    public SwipeItemTouchListener() {
    }

    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
        recyclerView.requestDisallowInterceptTouchEvent(findScrollableChildViewUnder(recyclerView, motionEvent) != null);
        return false;
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean b) {

    }

    private View findScrollableChildViewUnder(RecyclerView recyclerView, MotionEvent event) {
        if (recyclerView == null) {
            return null;
        }
        final int x = (int) event.getX();
        final int y = (int) event.getY();
        View child = recyclerView.findChildViewUnder(x, y);

        if (findCanScrollView(child) != null) {
            return child;
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

}