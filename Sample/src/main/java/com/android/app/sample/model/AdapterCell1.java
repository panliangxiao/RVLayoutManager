package com.android.app.sample.model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.app.sample.R;
import com.android.app.smartadapter.core.protocol.IRvSmartBinder;

/**
 * cell样式1
 */
public class AdapterCell1 implements IRvSmartBinder<AdpModel, View> {

    private TextView textView;

    @NonNull
    @Override
    public View createView(Context context, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_adapter1, parent, false);
        textView = view.findViewById(R.id.tv_text);
        return view;
    }

    @Override
    public void mountView(@NonNull AdpModel data, @NonNull View view) {
        textView.setText("样式" + data.getType() + " : " + data.i);
    }

    @Override
    public void unmountView(@NonNull AdpModel data, @NonNull View view) {

    }
}
