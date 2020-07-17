package com.android.app.sample.nested;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;


/**
 * Intercept touch event of RecyclerView binded
 */
public class SwipeItemTouchListener implements RecyclerView.OnItemTouchListener {

    Rect xy = new Rect();

    public SwipeItemTouchListener() {
    }

    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
        //判断当前事件坐标点下是否包含RecyclerView
        boolean hasScrollable = false;

        View view = findScrollableChildViewUnder(recyclerView, motionEvent);
        if (view != null){
            view.getGlobalVisibleRect(xy);
            hasScrollable = (motionEvent.getRawX() > xy.left && motionEvent.getRawX() < xy.right) && (motionEvent.getRawY() > xy.top && motionEvent.getRawY() < xy.bottom);
            Log.i("SwipeItemTouchListener", "motionEvent.getRawX() : " + motionEvent.getRawX() + "xy.left : " + xy.left + " xy.right : " + xy.right
                    + " motionEvent.getRawY() : " + motionEvent.getRawY() + " xy.top : " + xy.top + " xy.bottom : " + xy.bottom);
        }
        recyclerView.requestDisallowInterceptTouchEvent(hasScrollable);
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

        return findCanScrollView(child);
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