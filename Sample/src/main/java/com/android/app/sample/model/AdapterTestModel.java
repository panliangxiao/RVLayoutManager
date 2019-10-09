package com.android.app.sample.model;

import com.android.app.smartadapter.cell.ICell;

public class AdapterTestModel implements ICell {

    private String itemType;

    public AdapterTestModel(String itemType) {
        this.itemType = itemType;
    }

    @Override
    public String getType() {
        return itemType;
    }
}
