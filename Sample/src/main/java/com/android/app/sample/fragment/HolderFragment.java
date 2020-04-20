package com.android.app.sample.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.app.sample.R;
import com.android.app.sample.adapter.NestedAdapter;
import com.android.app.sample.adapter.NestedChildAdapter;

import java.util.ArrayList;
import java.util.List;

public class HolderFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<String> ll = new ArrayList<>();
    private NestedChildAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.holder_ifragment, container, false);
        recyclerView = view.findViewById(R.id.recycler_child);
        adapter = new NestedChildAdapter(getContext());
        for (int i = 0; i < 50 ; i++){
            ll.add("child : " + i);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter.setDataList(ll);
        recyclerView.setAdapter(adapter);
        return view;
    }
}
