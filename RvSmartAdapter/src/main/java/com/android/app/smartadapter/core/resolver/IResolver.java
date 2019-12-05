package com.android.app.smartadapter.core.resolver;

import android.view.View;

import com.android.app.smartadapter.cell.IRvSmartCell;
import com.android.app.smartadapter.core.protocol.IRvSmartBinder;

public interface IResolver<T extends IRvSmartCell> {

    IRvSmartBinder<T, ? extends View> create(String type);
}
