package com.android.app.smartadapter.core;

import android.view.View;

import com.android.app.smartadapter.cell.IRvSmartCell;
import com.android.app.smartadapter.core.protocol.IRvSmartBinder;

import java.util.HashMap;

public class RvCellWarehouse {
    private HashMap<String, Class<? extends IRvSmartBinder<? extends IRvSmartCell, ? extends View>>> mHolderCenter = new HashMap<>();

    private RvCellWarehouse(){

    }

    public static RvCellWarehouse getInstance(){
        return Holder.instance;
    }
    private static class Holder {
        private static final RvCellWarehouse instance = new RvCellWarehouse();
    }

    public <T extends IRvSmartCell> void register(String type, Class<? extends IRvSmartBinder<T, ? extends View>> clz){
        mHolderCenter.put(type, clz);
    }

    public <T extends IRvSmartCell> Class<? extends IRvSmartBinder<T, ? extends View>> getCell(String type){
        return (Class<? extends IRvSmartBinder<T, ? extends View>>) mHolderCenter.get(type);
    }

}
