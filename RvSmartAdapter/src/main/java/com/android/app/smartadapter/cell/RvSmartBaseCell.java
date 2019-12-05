package com.android.app.smartadapter.cell;

/**
 * 一种model的默认实现
 */
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

