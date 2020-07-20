package com.android.app.sample.anim.widget;

import android.content.Context;
import android.database.DataSetObservable;
import android.database.DataSetObserver;
import android.view.View;

/**
 * 轮播banner的适配器
 */
public abstract class AbsBannerAdapter {
    private final DataSetObservable mDataSetObservable = new DataSetObservable();

    public abstract int getCount();

    public abstract View getView(Context context, int index);

    public final void registerDataSetObserver(DataSetObserver observer) {
        mDataSetObservable.registerObserver(observer);
    }

    public final void unregisterDataSetObserver(DataSetObserver observer) {
        mDataSetObservable.unregisterObserver(observer);
    }

    public final void notifyDataSetChanged() {
        mDataSetObservable.notifyChanged();
    }

    public final void notifyDataSetInvalidated() {
        mDataSetObservable.notifyInvalidated();
    }
}
