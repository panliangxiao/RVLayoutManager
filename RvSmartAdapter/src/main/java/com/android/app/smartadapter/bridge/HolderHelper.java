package com.android.app.smartadapter.bridge;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

public interface HolderHelper<T, V extends View> {

    @NonNull
    V createView(Context context, ViewGroup parent);

    void mountView(@NonNull T data, @NonNull V view);

    void unmountView(@NonNull T data, @NonNull V view);

}
