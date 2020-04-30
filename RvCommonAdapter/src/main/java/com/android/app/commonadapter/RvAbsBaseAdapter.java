package com.android.app.commonadapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.android.app.commonadapter.holder.AbsBaseHolder;
import com.android.app.commonadapter.itemlistener.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @param <T> 数据model泛型
 */
public abstract class RvAbsBaseAdapter<T> extends RecyclerView.Adapter<AbsBaseHolder<T>> {


    protected List<T> mDataList;

    protected Context mContext;

    protected RecyclerView mRecyclerView;

    protected OnItemClickListener<T> onItemClickListener;

    public RvAbsBaseAdapter(Context context) {
        this.mContext = context;
        mDataList = new ArrayList<>();
    }


    public void setDataList(List<T> dataList) {
        setDataList(dataList, true);
    }

    public void setDataList(List<T> dataList, boolean notifyDataSetChanged) {
        mDataList.clear();
        if (dataList != null) {
            mDataList.addAll(dataList);
        }
        if (notifyDataSetChanged) {
            notifyDataSetChanged();
        }
    }


    public void setOnItemClickListener(OnItemClickListener<T> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public AbsBaseHolder<T> onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final AbsBaseHolder<T> holder = createHolder(viewGroup, i);
        if (holder == null) {
            throw new RuntimeException("createHolder can not be null");
        }
        //绑定时设置点击事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null && holder.getAdapterPosition() >= 0 ){
                    onItemClickListener.onItemClick(holder.itemView, mDataList.get(holder.getAdapterPosition()), holder.getAdapterPosition());
                }
            }
        });
        return holder;
    }

    /**
     * 抽象创建holder方法交给子类实现
     * @param viewGroup
     * @param i
     * @return
     */
    public abstract AbsBaseHolder<T> createHolder(@NonNull ViewGroup viewGroup, int i);

    /**
     * 统一调用holder绑定方法绑定view
     * @param tAbsBaseHolder
     * @param i
     */
    @Override
    public void onBindViewHolder(@NonNull AbsBaseHolder<T> tAbsBaseHolder, int i) {
        T bean = mDataList.get(i);
        Bundle bundle = new Bundle();
        bundle.putInt("height", mRecyclerView == null ? 0 : mRecyclerView.getMeasuredHeight());
        tAbsBaseHolder.bindHolder(bean, bundle, i);
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mRecyclerView = recyclerView;
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

}