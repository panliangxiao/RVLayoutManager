package com.android.app.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.app.layoutmanger.gallery.GalleryLayoutManager;
import com.android.app.sample.adapter.GalleryAdapter;

import java.util.ArrayList;
import java.util.List;

public class GalleryActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    private static List<String> mMinutes = new ArrayList<>();

    static {
        for (int i = 0; i < 60; i++) {
            mMinutes.add(i + "");
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        recyclerView = findViewById(R.id.recycler_gallery);

        recyclerView.setLayoutManager(new GalleryLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        GalleryAdapter adapter = new GalleryAdapter(this);
        adapter.setDataList(mMinutes);
        recyclerView.setAdapter(adapter);
        recyclerView.scrollToPosition(0);
    }

}
