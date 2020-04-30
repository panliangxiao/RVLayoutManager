package com.android.app.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
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
        recyclerView.addOnItemTouchListener(new SwipeItemTouchListener());
        //添加Android自带的分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        adapter = new NestedAdapter(this);
        for (int i = 0; i < 10; i++) {
            ll.add("pos : " + i);
        }
        adapter.setDataList(ll);
        recyclerView.setAdapter(adapter);
        recyclerView.requestFocus(); //设置焦点不需要
    }
}
