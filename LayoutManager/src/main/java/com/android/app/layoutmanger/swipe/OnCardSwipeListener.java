package com.android.app.layoutmanger.swipe;

import android.support.v7.widget.RecyclerView;

public interface OnCardSwipeListener {
    /**
     * 卡片还在滑动时回调
     *
     * @param viewHolder 该滑动卡片的viewHolder
     * @param ratio      滑动进度的比例
     * @param direction  卡片滑动的方向
     */
    void onSwiping(RecyclerView.ViewHolder viewHolder, float ratio, int direction);

    /**
     * 卡片完全滑出时回调
     *
     * @param viewHolder 该滑出卡片的viewHolder
     * @param position   该滑出卡片的数据position
     * @param direction  卡片滑出的方向 ItemTouchHelper.LEFT等
     */
    void onSwiped(RecyclerView.ViewHolder viewHolder, int position, int direction);

    /**
     * 所有的卡片全部滑出时回调
     */
    void onSwipedClear();
}