package com.android.app.sample.adapter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.android.app.commonadapter.holder.AbsBaseHolder;
import com.android.app.sample.R;

import org.w3c.dom.Text;

public class NestedHolder1 extends AbsBaseHolder<String> {
    TextView textView;
    public NestedHolder1(@NonNull View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.tv_text);
    }

    @Override
    public void bindHolder(String bean, Bundle extra, int position) {
        textView.setText(bean);
    }
}
