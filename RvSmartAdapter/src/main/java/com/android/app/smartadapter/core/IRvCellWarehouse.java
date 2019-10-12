package com.android.app.smartadapter.core;

import android.view.View;

import com.android.app.smartadapter.cell.IRvSmartCell;
import com.android.app.smartadapter.protocol.IRvSmartBinder;

import java.util.HashMap;

public class IRvCellWarehouse {
    private HashMap<String, Class<? extends IRvSmartBinder<? extends IRvSmartCell, ? extends View>>> mHolderCenter = new HashMap<>();

    private IRvCellWarehouse(){

    }

    public static IRvCellWarehouse getInstance(){
        return Holder.instance;
    }
    private static class Holder {
        private static final IRvCellWarehouse instance = new IRvCellWarehouse();
    }

    public <T extends IRvSmartCell> void register(String type, Class<? extends IRvSmartBinder<T, ? extends View>> clz){
        mHolderCenter.put(type, clz);
    }

    public <T extends IRvSmartCell> Class<? extends IRvSmartBinder<T, ? extends View>> getHolder(String type){
        return (Class<? extends IRvSmartBinder<T, ? extends View>>) mHolderCenter.get(type);
    }

}
