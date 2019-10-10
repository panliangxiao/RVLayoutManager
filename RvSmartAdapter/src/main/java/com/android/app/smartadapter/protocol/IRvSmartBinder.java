package com.android.app.smartadapter.protocol;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import com.android.app.smartadapter.cell.IRvSmartCell;

/**
 * holder的桥接接口定义相关方法模板
 * @param <T>
 * @param <V>
 */
public interface IRvSmartBinder<T extends IRvSmartCell, V extends View> {

    @NonNull
    V createView(Context context, ViewGroup parent);

    void mountView(@NonNull T data, @NonNull V view);

    void unmountView(@NonNull T data, @NonNull V view);

}
