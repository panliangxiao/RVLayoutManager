package com.android.app.sample.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.app.commonadapter.RvAbsBaseAdapter;
import com.android.app.commonadapter.holder.AbsBaseHolder;
import com.android.app.sample.R;

public class NestedChildAdapter extends RvAbsBaseAdapter<String> {
    public static final int ITEM_1 = 0;
    public static final int ITEM_2 = 1;

    public NestedChildAdapter(Context context) {
        super(context);
    }

    @Override
    public AbsBaseHolder<String> createHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.nested_item1, viewGroup, false);
        return new NestedHolder1(view);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1) {
            return ITEM_2;
        }

        return ITEM_1;
    }
}
