package com.android.app.smartadapter.factory;

import android.view.View;

import com.android.app.smartadapter.bridge.HolderHelper;

import java.util.HashMap;

public class CellWarehouse {
    private HashMap<String, Class<? extends HolderHelper>> mHolderCenter = new HashMap<>();

    private CellWarehouse(){

    }

    public static CellWarehouse getInstance(){
        return Inner.instance;
    }
    private static class Inner {
        private static final CellWarehouse instance = new CellWarehouse();
    }

    public void register(String type, Class<? extends HolderHelper> clz){
        mHolderCenter.put(type, clz);
    }

    public Class<? extends HolderHelper> getHolder(String type){
        return mHolderCenter.get(type);
    }

}
