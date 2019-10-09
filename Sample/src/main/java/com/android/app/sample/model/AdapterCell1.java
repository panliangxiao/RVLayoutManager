package com.android.app.sample.model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.app.sample.R;
import com.android.app.smartadapter.bridge.HolderHelper;
import com.android.app.smartadapter.cell.ICell;

public class AdapterCell1 implements HolderHelper<ICell, View> {
    @NonNull
    @Override
    public View createView(Context context, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item_adapter1, parent, false);
    }

    @Override
    public void mountView(@NonNull ICell data, @NonNull View view) {

    }

    @Override
    public void unmountView(@NonNull ICell data, @NonNull View view) {

    }
}
