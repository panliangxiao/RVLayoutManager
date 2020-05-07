package com.android.app.sample.suspension;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.android.app.sample.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class SuspensionActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private List<Area> mAreaList;
    private AreaAdapter mAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suspension);
        mRecyclerView = findViewById(R.id.recycler_parent);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        try {
            mAreaList = parse();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mAdapter = new AreaAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setDataList(mAreaList);
        RecyclerView.ItemDecoration itemDecoration = new SectionDecoration((int) getResources().getDimension(R.dimen.suspension_group_height), new GroupListener() {
            public String groupName(int pos) {
                if (pos < 0 || pos > mAreaList.size() -1){
                    return "";
                }
                return mAreaList.get(pos).pinyin;
            }
        });
        mRecyclerView.addItemDecoration(itemDecoration);
    }

    private List<Area> parse() throws IOException {
        InputStream is = getAssets().open("area.json");
        int length = is.available();
        byte[]  buffer = new byte[length];
        is.read(buffer);
        String result = new String(buffer);
        return JSON.parseObject(result, new TypeReference<List<Area>>(){});
    }
}
