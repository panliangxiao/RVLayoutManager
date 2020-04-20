package com.android.app.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.app.sample.adapter.NestedAdapter;

import java.util.ArrayList;
import java.util.List;

public class NestedActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    NestedAdapter adapter;
    List<String> ll = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nested);
        recyclerView = findViewById(R.id.recycler_parent);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addOnItemTouchListener(new SwipeItemTouchListener(recyclerView));
        adapter = new NestedAdapter(this);
        for (int i = 0; i < 20 ; i ++){
            ll.add(i + " : pos");
        }
        adapter.setDataList(ll);
        recyclerView.setAdapter(adapter);
    }
}
