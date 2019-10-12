package com.android.app.smartadapter.protocol;

import android.view.View;

import com.android.app.smartadapter.cell.IRvSmartCell;
import com.android.app.smartadapter.core.RvCellWarehouse;
import com.android.app.smartadapter.core.resolver.IResolver;

/**
 * 创建代理binder解决器
 */
public class RvSmartBinderResolver<RV extends IRvSmartBinder<? extends IRvSmartCell, ? extends View>> implements IResolver<RV> {

    @Override
    public RV create(String type) {
        try {
            Class clz = RvCellWarehouse.getInstance().getCell(type);
            return (RV) clz.newInstance();
        }catch (Throwable error){

        }
        return null;
    }
}