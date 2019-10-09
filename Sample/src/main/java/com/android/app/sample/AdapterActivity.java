package com.android.app.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.app.sample.model.AdapterCell1;
import com.android.app.sample.model.AdapterCell2;
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


        IRvCellWarehouse.getInstance().register("0", AdapterCell1.class);
        IRvCellWarehouse.getInstance().register("1", AdapterCell2.class);


        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        for (int i = 0; i < 50; i ++){
            RvSmartBaseCell model = new RvSmartBaseCell(i % 2 + "");
            list.add(model);
        }

        RVSmartAdapter<IRvSmartCell> smartAdapter = new RVSmartAdapter<>();
        smartAdapter.setData(list);
        recyclerView.setAdapter(smartAdapter);

    }
}
