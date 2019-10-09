package com.android.app.sample.model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.app.sample.R;
import com.android.app.smartadapter.bridge.HolderHelper;
import com.android.app.smartadapter.cell.ICell;

public class AdapterCell2 implements HolderHelper<ICell, View> {

    private TextView textView;

    @NonNull
    @Override
    public View createView(Context context, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_adapter2, parent, false);
        textView = view.findViewById(R.id.tv_text);
        return view;
    }

    @Override
    public void mountView(@NonNull ICell data, @NonNull View view) {
        textView.setText("测试 : " + data.getType());
    }

    @Override
    public void unmountView(@NonNull ICell data, @NonNull View view) {

    }
}
