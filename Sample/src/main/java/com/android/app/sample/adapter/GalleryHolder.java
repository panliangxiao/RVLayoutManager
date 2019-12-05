package com.android.app.sample.adapter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import com.android.app.commonadapter.holder.AbsBaseHolder;

public class GalleryHolder extends AbsBaseHolder<String> {
    public GalleryHolder(@NonNull View itemView) {
        super(itemView);
    }

    @Override
    public void bindHolder(String bean, Bundle extra, int position) {

    }
}
