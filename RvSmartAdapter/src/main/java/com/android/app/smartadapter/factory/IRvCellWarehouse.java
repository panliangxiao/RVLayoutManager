package com.android.app.smartadapter.factory;

import com.android.app.smartadapter.protocol.IRvSmartBinder;

import java.util.HashMap;

public class IRvCellWarehouse {
    private HashMap<String, Class<? extends IRvSmartBinder>> mHolderCenter = new HashMap<>();

    private IRvCellWarehouse(){

    }

    public static IRvCellWarehouse getInstance(){
        return Inner.instance;
    }
    private static class Inner {
        private static final IRvCellWarehouse instance = new IRvCellWarehouse();
    }

    public void register(String type, Class<? extends IRvSmartBinder> clz){
        mHolderCenter.put(type, clz);
    }

    public Class<? extends IRvSmartBinder> getHolder(String type){
        return mHolderCenter.get(type);
    }

}
