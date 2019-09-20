package com.android.app.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.app.layoutmanger.swipe.CardSwipeLayoutManager;
import com.android.app.layoutmanger.swipe.OnCardSwipeListener;

import java.util.ArrayList;
import java.util.List;

public class CardSwipeActivity extends AppCompatActivity {

    private static final String TAG = CardSwipeActivity.class.getSimpleName();

    private RecyclerView mRecyclerView;
    private MyAdapter mAdapter = new MyAdapter();

    private List<UserInfo> mList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_swipe);
        mRecyclerView = findViewById(R.id.recycler_view);
        addData();
        CardSwipeLayoutManager swipeLayoutManager = new CardSwipeLayoutManager(new OnCardSwipeListener() {
            @Override
            public void onSwiping(RecyclerView.ViewHolder viewHolder, float ratio, int direction) {
                Log.i(TAG, "ratio : " + ratio + "-" + "direction : " + direction);
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int position, int direction) {
                if (mList != null) {
                    mList.remove(position);
                }
            }

            @Override
            public void onSwipedClear() {
//                addData();
            }
        });
        mRecyclerView.setLayoutManager(swipeLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    /**
     * 向集合中添加数据
     */
    private void addData(){
        String[] titles = {"Acknowledging", "Belief", "Confidence", "Dreaming", "Happiness", "Confidence"};
        String[] says = {
                "Do one thing at a time, and do well.",
                "Keep on going never give up.",
                "Whatever is worth doing is worth doing well.",
                "I can because i think i can.",
                "Jack of all trades and master of none.",
                "Keep on going never give up.",
                "Whatever is worth doing is worth doing well.",
        };
        int[] bgs = {
                R.mipmap.img_slide_1,
                R.mipmap.img_slide_2,
                R.mipmap.img_slide_3,
                R.mipmap.img_slide_4,
                R.mipmap.img_slide_5,
                R.mipmap.img_slide_6
        };

        for (int i = 0; i < 6; i++) {
            mList.add(new UserInfo(bgs[i],titles[i],says[i]));
        }
    }

    /**
     * 适配器
     */
    class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(CardSwipeActivity.this).inflate(R.layout.item_swipe, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            UserInfo bean = mList.get(position);
            holder.imgBg.setImageResource(bean.getItemBg());
            holder.tvTitle.setText(bean.getTitle());
            holder.userSay.setText(bean.getUserSay());
        }

        @Override
        public int getItemCount() {
            return mList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            ImageView imgBg;
            TextView tvTitle;
            TextView userSay;

            public ViewHolder(View itemView) {
                super(itemView);
                imgBg = itemView.findViewById(R.id.img_bg);
                tvTitle = itemView.findViewById(R.id.tv_title);
                userSay = itemView.findViewById(R.id.tv_user_say);
            }
        }
    }

}
