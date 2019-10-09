package com.android.app.smartadapter.protocol;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import com.android.app.smartadapter.cell.IRvSmartCell;

public interface IRvSmartBinder<T extends IRvSmartCell, V extends View> {

    @NonNull
    V createView(Context context, ViewGroup parent);

    void mountView(@NonNull T data, @NonNull V view);

    void unmountView(@NonNull T data, @NonNull V view);

}
