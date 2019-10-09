package com.android.app.smartadapter.cell;

public class RvSmartBaseCell implements IRvSmartCell {
    private String itemType;

    public RvSmartBaseCell(String itemType) {
        this.itemType = itemType;
    }

    @Override
    public String getType() {
        return itemType;
    }
}

