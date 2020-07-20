package com.android.app.sample.anim.widget;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.database.DataSetObserver;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public class BannerLayout extends FrameLayout {

    private AbsBannerAdapter mAdapter;
    private int mCounter = 0; //计数器
    private LayoutTransition mTransition = new LayoutTransition();
    private DataSetObserver mObserver = new DataSetObserver() {

        @Override
        public void onChanged() {
            init();
        }

        @Override
        public void onInvalidated() {
            // 没什么用，暂不做处理
        }
    };

    private Runnable mRefreshRunnable = new Runnable() {
        @Override
        public void run() {
            if (mCounter >= Integer.MAX_VALUE) {
                mCounter = 0;
            }
            mCounter++;

            addView();
            postDelayed(mRefreshRunnable, 3000);
        }
    };

    private Runnable mAddRunnable = new Runnable() {
        @Override
        public void run() {
            View view = mAdapter.getView(getContext(), mCounter);
            if (view != null) {
                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                addView(view, params);
            }
        }
    };

    public BannerLayout(@NonNull Context context) {
        super(context);
        init();
    }

    public BannerLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BannerLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void setAdapter(AbsBannerAdapter adapter) {
        if (mAdapter == adapter) {
            return;
        }
        if (mAdapter != null) {
            mAdapter.unregisterDataSetObserver(mObserver);
        }
        mAdapter = adapter;
        if (mAdapter != null) {
            mAdapter.registerDataSetObserver(mObserver);
            mAdapter.notifyDataSetChanged();
        } else {
            init();
        }
    }

    private void init() {
        removeAllViews();

        mTransition.setAnimator(LayoutTransition.APPEARING, appear());
        mTransition.setDuration(LayoutTransition.APPEARING, 400);
        mTransition.setStartDelay(LayoutTransition.APPEARING, 0);//源码中带有默认300毫秒的延时，需要移除，不然view添加效果不好！！

        mTransition.setAnimator(LayoutTransition.DISAPPEARING, disappear());
        mTransition.setDuration(LayoutTransition.DISAPPEARING, 400);
        mTransition.setStartDelay(LayoutTransition.DISAPPEARING, 0);
        setLayoutTransition(mTransition);

        if (mAdapter == null) {
            return;
        }

        addView();
        postDelayed(mRefreshRunnable, 3000);

    }

    private void addView(){
        if (getChildCount() > 0) {
            removeViewAt(0);
        }
        postDelayed(mAddRunnable, 400);
    }


    private Animator appear() {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(ObjectAnimator.ofFloat(null, "Alpha", 0.2f, 1.0f),
                ObjectAnimator.ofFloat(null, "translationY", 400, 0));
        return animatorSet;
    }

    private Animator disappear() {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(ObjectAnimator.ofFloat(null, "Alpha", 1.0f, 0.2f),
                ObjectAnimator.ofFloat(null, "translationY", 0, -400));
        return animatorSet;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(mRefreshRunnable);
        removeCallbacks(mAddRunnable);
    }
}
