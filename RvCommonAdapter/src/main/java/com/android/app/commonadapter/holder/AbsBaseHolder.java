package com.android.app.commonadapter.holder;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 *
 * @param <T> 数据model泛型
 */
public abstract class AbsBaseHolder<T> extends RecyclerView.ViewHolder {

    protected Context mContext;


    public AbsBaseHolder(@NonNull View itemView) {
        super(itemView);
        mContext = itemView.getContext();
    }

    /**
     *
     * @param bean
     * @param extra 通过bundle携带adapter额外绑定配置参数用于渲染view
     * @param position
     */
    public abstract void bindHolder(T bean, Bundle extra, int position);

}
