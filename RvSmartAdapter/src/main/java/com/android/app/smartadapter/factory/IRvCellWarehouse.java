package com.android.app.smartadapter.factory;

import android.view.View;

import com.android.app.smartadapter.cell.IRvSmartCell;
import com.android.app.smartadapter.protocol.IRvSmartBinder;

import java.util.HashMap;

public class IRvCellWarehouse {
    private HashMap<String, Class<? extends IRvSmartBinder<? extends IRvSmartCell, ? extends View>>> mHolderCenter = new HashMap<>();

    private IRvCellWarehouse(){

    }

    public static IRvCellWarehouse getInstance(){
        return Inner.instance;
    }
    private static class Inner {
        private static final IRvCellWarehouse instance = new IRvCellWarehouse();
    }

    public void register(String type, Class<? extends IRvSmartBinder<? extends IRvSmartCell, ? extends View>> clz){
        mHolderCenter.put(type, clz);
    }

    public Class<? extends IRvSmartBinder<? extends IRvSmartCell, ? extends View>> getHolder(String type){
        return mHolderCenter.get(type);
    }

}
