package com.android.app.sample.suspension;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.android.app.commonadapter.holder.AbsBaseHolder;
import com.android.app.sample.R;

public class AreaHolder extends AbsBaseHolder<Area> {
    TextView textView;
    public AreaHolder(@NonNull View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.tv_text);
    }

    @Override
    public void bindHolder(Area bean, Bundle extra, int position) {
        textView.setText(bean.text);
    }
}
