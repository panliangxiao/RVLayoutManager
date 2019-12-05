package com.android.app.sample;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.app.layoutmanger.gallery.GalleryLayoutManager;

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
        recyclerView.setAdapter(new MyAdapter(mMinutes));
        recyclerView.scrollToPosition(0);
    }


    class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
        private int[] mColors = {Color.YELLOW,Color.RED};
        private List<String> mList ;

        public MyAdapter(List<String> list) {
            this.mList = list;
        }

        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(GalleryActivity.this).inflate(R.layout.item_gellary,parent,false);
            return new MyAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {
        }

        @Override
        public int getItemCount() {
            return mList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder{
            public ViewHolder(View itemView) {
                super(itemView);
            }
        }
    }
}
