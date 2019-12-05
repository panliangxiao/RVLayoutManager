package com.android.app.smartadapter.core.protocol;

import android.view.View;

import com.android.app.smartadapter.cell.IRvSmartCell;
import com.android.app.smartadapter.core.RvCellWarehouse;
import com.android.app.smartadapter.core.resolver.IResolver;

/**
 * 创建代理binder解决器
 */
public class RvSmartBinderResolver<T extends IRvSmartCell> implements IResolver<T> {

    @Override
    public IRvSmartBinder<T, ? extends View> create(String type) {
        try {
            Class<? extends IRvSmartBinder<T, ? extends View>> clz = RvCellWarehouse.getInstance().getCell(type);
            return clz.newInstance();
        }catch (Throwable error){

        }
        return null;
    }
}