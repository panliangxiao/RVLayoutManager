package com.android.app.sample.suspension;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.app.commonadapter.RvAbsBaseAdapter;
import com.android.app.commonadapter.holder.AbsBaseHolder;
import com.android.app.sample.R;

public class AreaAdapter extends RvAbsBaseAdapter<Area> {
    public AreaAdapter(Context context) {
        super(context);
    }

    @Override
    public AbsBaseHolder<Area> createHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.nested_item1, viewGroup, false);
        return new AreaHolder(view);
    }


}
