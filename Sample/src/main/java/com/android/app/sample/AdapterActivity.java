package com.android.app.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.app.sample.model.AdapterCell1;
import com.android.app.sample.model.AdapterCell2;
import com.android.app.sample.model.AdapterTestModel;
import com.android.app.smartadapter.RVSmartAdapter;
import com.android.app.smartadapter.cell.ICell;
import com.android.app.smartadapter.factory.CellWarehouse;

import java.util.ArrayList;
import java.util.List;

public class AdapterActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    List<ICell> list = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adapter);

        recyclerView = findViewById(R.id.recycler_view);


        CellWarehouse.getInstance().register("0", AdapterCell1.class);
        CellWarehouse.getInstance().register("1", AdapterCell2.class);


        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        for (int i = 0; i < 50; i ++){
            AdapterTestModel model = new AdapterTestModel(i % 2 + "");
            list.add(model);
        }

        RVSmartAdapter<ICell> smartAdapter = new RVSmartAdapter<>();
        smartAdapter.setData(list);
        recyclerView.setAdapter(smartAdapter);

    }
}
