package com.android.app.smartadapter.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.app.smartadapter.bridge.HolderHelper;

public class RVSmartHolder<T, V extends View> extends RecyclerView.ViewHolder {

    public HolderHelper<T, V> controller;

    public V itemView;

    public T data;

    public RVSmartHolder(@NonNull V itemView, @NonNull HolderHelper<T, V> binder) {
        super(itemView);
        this.itemView = itemView;
        this.controller = binder;
    }

    public void bindHolder(T data){
        controller.mountView(data, itemView);
        this.data = data;
    }


    public void unBindHolder(){
        if (data != null){
            controller.unmountView(data, itemView);
        }
    }

}
