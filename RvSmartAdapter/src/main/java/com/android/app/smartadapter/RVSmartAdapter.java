package com.android.app.smartadapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.util.ArrayMap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import com.android.app.smartadapter.protocol.IRvSmartBinder;
import com.android.app.smartadapter.cell.IRvSmartCell;
import com.android.app.smartadapter.core.IRvCellWarehouse;
import com.android.app.smartadapter.holder.RvSmartHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class RVSmartAdapter<T extends IRvSmartCell> extends RecyclerView.Adapter<RvSmartHolder<T, ? extends View>> {

    private List<T> mDataList = new ArrayList<>();

    //储存id和cell type之间的对应关系
    private final SparseArray<String> mId2Types = new SparseArray<>(64);
    //用于自增的id值
    private AtomicInteger mTypeId = new AtomicInteger(0);
    //存储cell type 到id之间的对应
    private final Map<String, Integer> mStrKeys = new ArrayMap<>(64);


    public void setData(List<T> list){
        mDataList.clear();
        mDataList.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RvSmartHolder<T, ? extends View> onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        String type = getCellTypeFromItemType(i);
        try {
            Class<? extends IRvSmartBinder<T, ? extends View>> clz = IRvCellWarehouse.getInstance().getHolder(type);
            IRvSmartBinder<T, ? extends View> holder = clz.newInstance();
            return createViewHolder(holder, viewGroup.getContext(), viewGroup);
        }catch (Throwable error){

        }
        return null;
    }

    public <V extends View> RvSmartHolder<T, V> createViewHolder(
            @NonNull final IRvSmartBinder<T, V> binder, @NonNull final Context context, final ViewGroup parent){
        V view = binder.createView(context, parent);
        return new RvSmartHolder<>(view, binder);
    }


    @Override
    public void onBindViewHolder(@NonNull RvSmartHolder<T, ? extends View> rvSmartHolder, int i) {
        T data = mDataList.get(i);
        rvSmartHolder.bindHolder(data);
    }

    @Override
    public int getItemViewType(int position) {
        T data = mDataList.get(position);
        if (data == null)
            return -1;
        // we should use getType()
        String typeKey = data.getType();
        if (!mStrKeys.containsKey(typeKey)) {
            int newType = mTypeId.getAndIncrement();
            mStrKeys.put(typeKey, newType);
            mId2Types.put(newType, data.getType());
        }
        return mStrKeys.get(typeKey).intValue();

    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public String getCellTypeFromItemType(int viewType) {
        if (mId2Types.indexOfKey(viewType) < 0) {
            throw new IllegalStateException("Can not found item.type for viewType: " + viewType);
        }
        return mId2Types.get(viewType);
    }

    @Override
    public void onViewRecycled(@NonNull RvSmartHolder holder) {
        super.onViewRecycled(holder);
        holder.unBindHolder();
    }
}
