package com.android.app.commonadapter.itemlistener;

import android.view.View;

/**
 *
 * @param <T> 数据model泛型
 */
public interface OnItemClickListener<T> {
    /**
     *
     * @param view 列表item View
     * @param data 具体item model
     * @param position 位置
     */
    void onItemClick(View view, T data, int position);
}