package com.android.app.smartadapter.core;

import android.view.View;

import com.android.app.smartadapter.cell.IRvSmartCell;
import com.android.app.smartadapter.protocol.IRvSmartBinder;

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

    public void register(String type, Class<? extends IRvSmartBinder<? extends IRvSmartCell, ? extends View>> clz){
        mHolderCenter.put(type, clz);
    }

    public Class<? extends IRvSmartBinder<? extends IRvSmartCell, ? extends View>> getCell(String type){
        return mHolderCenter.get(type);
    }

}
