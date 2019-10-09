package com.android.app.smartadapter.cell;

public class RvSmartBaseCell implements IRvSmartCell {
    protected String itemType;

    public RvSmartBaseCell(String itemType) {
        this.itemType = itemType;
    }

    @Override
    public String getType() {
        return itemType;
    }
}

