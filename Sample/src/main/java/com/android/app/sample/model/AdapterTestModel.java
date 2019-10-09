package com.android.app.sample.model;

import com.android.app.smartadapter.cell.IRvSmartCell;

public class AdapterTestModel implements IRvSmartCell {

    private String itemType;

    public AdapterTestModel(String itemType) {
        this.itemType = itemType;
    }

    @Override
    public String getType() {
        return itemType;
    }
}
