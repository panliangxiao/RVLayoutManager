package com.android.app.sample.adapter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.android.app.commonadapter.holder.AbsBaseHolder;
import com.android.app.sample.R;
import com.android.app.sample.fragment.HolderFragment;
import com.android.app.sample.fragment.HolderFragment2;

import java.util.ArrayList;
import java.util.List;

public class NestedHolder2 extends AbsBaseHolder<String> {


    ViewPager viewPager;
    FragmentAdapter adapter;
    List<Fragment> fragments = new ArrayList<>();

    public NestedHolder2(@NonNull View itemView) {
        super(itemView);
        viewPager = itemView.findViewById(R.id.container);
        fragments.add(new HolderFragment());
    }

    @Override
    public void bindHolder(String bean, Bundle extra, int position) {

        if (itemView.getContext() instanceof FragmentActivity){
            adapter = new FragmentAdapter(((FragmentActivity) itemView.getContext()).getSupportFragmentManager());
            viewPager.setAdapter(adapter);
            adapter.setFragments(fragments);

        }

    }
}
