package com.android.app.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.app.sample.model.AdapterCell1;
import com.android.app.sample.model.AdapterCell2;
import com.android.app.sample.model.AdpModel;
import com.android.app.smartadapter.RVSmartAdapter;
import com.android.app.smartadapter.cell.IRvSmartCell;
import com.android.app.smartadapter.cell.RvSmartBaseCell;
import com.android.app.smartadapter.core.IRvCellWarehouse;

import java.util.ArrayList;
import java.util.List;

public class AdapterActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    List<IRvSmartCell> list = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adapter);

        recyclerView = findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        for (int i = 0; i < 50; i ++){
            AdpModel model = new AdpModel(i % 2 + "");
            model.i = i + "";
            list.add(model);
        }

        RVSmartAdapter<IRvSmartCell> smartAdapter = new RVSmartAdapter<>();
        smartAdapter.setData(list);
        recyclerView.setAdapter(smartAdapter);

    }
}
