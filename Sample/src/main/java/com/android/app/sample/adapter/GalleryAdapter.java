package com.android.app.sample.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.app.commonadapter.RvAbsBaseAdapter;
import com.android.app.commonadapter.holder.AbsBaseHolder;
import com.android.app.sample.R;

public class GalleryAdapter extends RvAbsBaseAdapter<String> {
    public GalleryAdapter(Context context) {
        super(context);
    }

    @Override
    public AbsBaseHolder<String> createHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_gellary, viewGroup,false);
        return new GalleryHolder(view);
    }
}
