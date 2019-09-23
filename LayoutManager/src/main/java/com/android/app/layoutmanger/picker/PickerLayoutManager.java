package com.android.app.layoutmanger.picker;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 时间选择器控件
 */
public class PickerLayoutManager extends LinearLayoutManager {
    private static final String TAG = PickerLayoutManager.class.getSimpleName();

    private float mScale = 0.5f;
    /**
     * 是否具有透明效果
     */
    private boolean mIsAlpha = true;
    private LinearSnapHelper mLinearSnapHelper; //使用LinearSnapHelper
    private OnSelectedViewListener mOnSelectedViewListener;
    private int mItemViewWidth;
    private int mItemViewHeight;
    private int mItemCount = -1;
    private RecyclerView mRecyclerView;
    private int mOrientation;

    public PickerLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
        this.mLinearSnapHelper = new LinearSnapHelper();
        this.mOrientation = orientation;
    }

    public PickerLayoutManager(Context context, int orientation, boolean reverseLayout, int itemCount,float scale,boolean isAlpha) {
        super(context, orientation, reverseLayout);
        this.mLinearSnapHelper = new LinearSnapHelper();
        this.mItemCount = itemCount;
        this.mOrientation = orientation;
        this.mIsAlpha = isAlpha;
        this.mScale = scale;
    }

    /**
     * 添加LinearSnapHelper
     * 获取RecyclerView
     */
    @Override
    public void onAttachedToWindow(RecyclerView view) {
        super.onAttachedToWindow(view);
        mRecyclerView = view;
        mLinearSnapHelper.attachToRecyclerView(view);
    }

    /**
     * 指定数量后关闭自动布局
     * @return
     */
    @Override
    public boolean isAutoMeasureEnabled() {
        if (mItemCount > 0)
            return false;
        return super.isAutoMeasureEnabled();
    }

    /**
     * 没有指定显示条目的数量时，走原有Measure逻辑
     * 指定显示条目的数量时，根据方向分别计算RecyclerView的宽高
     */
    @Override
    public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int widthSpec, int heightSpec) {
        if (state.getItemCount() == 0) {
            removeAndRecycleAllViews(recycler);
            return;
        }

        View view = recycler.getViewForPosition(0);
        measureChildWithMargins(view, widthSpec, heightSpec);

        mItemViewWidth = view.getMeasuredWidth();
        mItemViewHeight = view.getMeasuredHeight();

        if (mItemCount > 0) {

            if (mOrientation == HORIZONTAL) {
                int paddingHorizontal = (mItemCount - 1) * mItemViewWidth / 2;
                //允许子view绘制在padding区域
                mRecyclerView.setClipToPadding(false);
                mRecyclerView.setPadding(paddingHorizontal,0,paddingHorizontal,0);
                int newWidthSpec = View.MeasureSpec.makeMeasureSpec(mItemViewWidth * mItemCount, View.MeasureSpec.EXACTLY);
                super.onMeasure(recycler,state,newWidthSpec,heightSpec);
            } else if (mOrientation == VERTICAL) {
                int paddingVertical = (mItemCount - 1) * mItemViewHeight / 2;
                mRecyclerView.setClipToPadding(false);
                mRecyclerView.setPadding(0, paddingVertical, 0, paddingVertical);
                int newHeightSpec = View.MeasureSpec.makeMeasureSpec(mItemViewHeight * mItemCount, View.MeasureSpec.EXACTLY);
                super.onMeasure(recycler, state, widthSpec, newHeightSpec);
            }
        }else {
            if (mOrientation == HORIZONTAL) {
                int paddingHorizontal = (getWidth() - mItemViewWidth) / 2;
                //允许子view绘制在padding区域
                mRecyclerView.setClipToPadding(false);
                mRecyclerView.setPadding(paddingHorizontal,0,paddingHorizontal,0);
            } else if (mOrientation == VERTICAL) {
                int paddingVertical = (getHeight() - mItemViewHeight) / 2;
                mRecyclerView.setClipToPadding(false);
                mRecyclerView.setPadding(0, paddingVertical, 0, paddingVertical);
            }
            super.onMeasure(recycler, state, widthSpec, heightSpec);
        }

    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        super.onLayoutChildren(recycler, state);
        if (getItemCount() < 0 || state.isPreLayout()) return;

        if (mOrientation == HORIZONTAL){
            scaleHorizontalChildView();
        }else if (mOrientation == VERTICAL){
            scaleVerticalChildView();
        }

    }

    @Override
    public int scrollHorizontallyBy(int dx, RecyclerView.Recycler recycler, RecyclerView.State state) {
        scaleHorizontalChildView();
        return super.scrollHorizontallyBy(dx, recycler, state);
    }

    @Override
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {
        if (mOrientation == HORIZONTAL){
            scaleHorizontalChildView();
        }else if (mOrientation == VERTICAL){
            scaleVerticalChildView();
        }
        super.smoothScrollToPosition(recyclerView, state, position);
    }

    @Override
    public void scrollToPosition(int position) {
        if (mOrientation == HORIZONTAL){
            scaleHorizontalChildView();
        }else if (mOrientation == VERTICAL){
            scaleVerticalChildView();
        }
        super.scrollToPosition(position);
    }

    @Override
    public void scrollToPositionWithOffset(int position, int offset) {
        if (mOrientation == HORIZONTAL){
            scaleHorizontalChildView();
        }else if (mOrientation == VERTICAL){
            scaleVerticalChildView();
        }
        super.scrollToPositionWithOffset(position, offset);
    }

    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        scaleVerticalChildView();
        return super.scrollVerticallyBy(dy, recycler, state);
    }

    /**
     * 横向情况下的缩放
     */
    private void scaleHorizontalChildView() {
        float mid = getWidth() / 2.0f;
        for (int i = 0; i < getChildCount(); i++) {
            View child =  getChildAt(i);
            float childMid = (getDecoratedLeft(child) + getDecoratedRight(child)) / 2.0f;
            float scale = 1.0f + (-1 * (1 - mScale)) * (Math.min(mid, Math.abs(mid - childMid))) / mid;
            child.setScaleX(scale);
            child.setScaleY(scale);
            if (mIsAlpha) {
                child.setAlpha(scale);
            }
        }
    }

    /**
     * 竖向方向上的缩放
     */
    private void scaleVerticalChildView(){
        float mid = getHeight() / 2.0f;
        for (int i = 0; i < getChildCount(); i++) {
            View child =  getChildAt(i);
            float childMid = (getDecoratedTop(child) + getDecoratedBottom(child)) / 2.0f;
            float scale = 1.0f + (-1 *  (1 - mScale)) * (Math.min(mid, Math.abs(mid - childMid))) / mid;
            child.setScaleX(scale);
            child.setScaleY(scale);
            if (mIsAlpha) {
                child.setAlpha(scale);
            }
        }
    }


    /**
     * 当滑动停止时触发回调
     * @param state
     */
    @Override
    public void onScrollStateChanged(int state) {
        super.onScrollStateChanged(state);
        if (state == RecyclerView.SCROLL_STATE_IDLE) {
            if (mOnSelectedViewListener != null && mLinearSnapHelper != null) {
                View view = mLinearSnapHelper.findSnapView(this);
                int position = getPosition(view);
                mOnSelectedViewListener.onSelectedView(view,position);
            }
        }
    }


    public void setOnSelectedViewListener(OnSelectedViewListener listener) {
        this.mOnSelectedViewListener = listener;
    }

    /**
     * 停止时，显示在中间的View的监听
     */
    public interface OnSelectedViewListener {
        void onSelectedView(View view,int position);
    }
}
