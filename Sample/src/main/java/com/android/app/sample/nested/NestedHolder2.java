package com.android.app.sample.nested;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.android.app.commonadapter.holder.AbsBaseHolder;
import com.android.app.sample.R;
import com.android.app.sample.fragment.HolderFragment;

import java.util.ArrayList;
import java.util.List;

public class NestedHolder2 extends AbsBaseHolder<String> {


    ViewPager viewPager;
    FragmentAdapter adapter;
    List<Fragment> fragments = new ArrayList<>();

    public NestedHolder2(@NonNull View itemView) {
        super(itemView);
        viewPager = itemView.findViewById(R.id.container);

    }

    @Override
    public void bindHolder(String bean, Bundle extra, int position) {
        int height = extra.getInt("height", 0);
        ViewGroup.LayoutParams params = itemView.getLayoutParams();
        params.height = height;
        if (itemView.getContext() instanceof FragmentActivity){
            adapter = new FragmentAdapter(((FragmentActivity) itemView.getContext()).getSupportFragmentManager());
            viewPager.setAdapter(adapter);
            fragments.clear();
            fragments.add(new HolderFragment());
            adapter.setFragments(fragments);

        }

    }
}
