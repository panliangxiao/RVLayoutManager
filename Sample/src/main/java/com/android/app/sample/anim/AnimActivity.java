package com.android.app.sample.anim;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.android.app.sample.R;
import com.android.app.sample.anim.widget.AbsBannerAdapter;
import com.android.app.sample.anim.widget.BannerLayout;

public class AnimActivity extends AppCompatActivity {

    private BannerLayout mViewContainer;

    private int padding = 5;

    AbsBannerAdapter adapter = new AbsBannerAdapter() {
        @Override
        public int getCount() {
            return 1;
        }

        @Override
        public View getView(Context context, int index) {
            View view1 = LayoutInflater.from(AnimActivity.this).inflate(R.layout.item_adapter1, mViewContainer, false);
            ((TextView) view1).setText("添加" + index);
            return view1;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anim_activity);
        mViewContainer = findViewById(R.id.view_container);
        mViewContainer.setAdapter(adapter);
    }

    private Animator getAppearingAnimation() {
        AnimatorSet mSet = new AnimatorSet();
        mSet.playTogether(/*ObjectAnimator.ofFloat(null, "ScaleX", 2.0f, 1.0f),
                ObjectAnimator.ofFloat(null, "ScaleY", 2.0f, 1.0f),*/
                ObjectAnimator.ofFloat(null, "Alpha", 0.2f, 1.0f),
                ObjectAnimator.ofFloat(null, "translationY", 400, 0));
        return mSet;
    }

    private Animator getDisappearingAnimation() {
        AnimatorSet mSet = new AnimatorSet();
        mSet.playTogether(/*ObjectAnimator.ofFloat(null, "ScaleX", 1.0f, 0f),
                ObjectAnimator.ofFloat(null, "ScaleY", 1.0f, 0f),*/
                ObjectAnimator.ofFloat(null, "Alpha", 1.0f, 0.2f), ObjectAnimator.ofFloat(null, "translationY", 0, -400));
        return mSet;
    }

    private Animator getDisappearingChangeAnimation() {
        PropertyValuesHolder pvhLeft = PropertyValuesHolder.ofInt("left", 0, 0);
        PropertyValuesHolder pvhTop = PropertyValuesHolder.ofInt("top", 0, 0);
        PropertyValuesHolder pvhRight = PropertyValuesHolder.ofInt("right", 0, 0);
        PropertyValuesHolder pvhBottom = PropertyValuesHolder.ofInt("bottom", 0, 0);
        PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat("scaleX", 1.0f, 0f, 1.0f);
        PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat("scaleY", 1.0f, 0f, 1.0f);
        PropertyValuesHolder rotate = PropertyValuesHolder.ofFloat("rotation", 0, 0, 0);
        return ObjectAnimator.ofPropertyValuesHolder((Object) null, pvhLeft, pvhTop, pvhRight, pvhBottom, scaleX, scaleY, rotate);
    }

    private Animator getAppearingChangeAnimation() {
        PropertyValuesHolder pvhLeft = PropertyValuesHolder.ofInt("left", 0, 0);
        PropertyValuesHolder pvhTop = PropertyValuesHolder.ofInt("top", 0, 0);
        PropertyValuesHolder pvhRight = PropertyValuesHolder.ofInt("right", 0, 0);
        PropertyValuesHolder pvhBottom = PropertyValuesHolder.ofInt("bottom", 0, 0);
        PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat("scaleX", 1.0f, 3f, 1.0f);
        PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat("scaleY", 1.0f, 3f, 1.0f);
        return ObjectAnimator.ofPropertyValuesHolder((Object) null, pvhLeft, pvhTop, pvhRight, pvhBottom, scaleX, scaleY);
    }

    @SuppressLint("StringFormatInvalid")
    public void addViewToParent(View view) {
        padding +=5;
        mViewContainer.setPadding(padding, padding, padding, padding);

//        if (isTransitionFinish) {
//            isTransitionFinish = false;
//            if (mViewContainer.getChildCount() > 0){
//                mViewContainer.removeViewAt(0);
//
//            }
//            mViewContainer.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    View view1 = LayoutInflater.from(AnimActivity.this).inflate(R.layout.item_adapter1, mViewContainer, false);
//                    ((TextView) view1).setText("添加" + mViewCount);
//                    mViewContainer.addView(view1, 0);
//                    mViewCount++;
//                }
//            }, 400);
//
//        }
    }

    public void removeViewFromParent(View view) {
        padding -=5;
        mViewContainer.setPadding(padding, padding, padding, padding);

//        if (isTransitionFinish) {
//            if (mViewCount >= 1) {
//                isTransitionFinish = false;
//                mViewContainer.removeViewAt((0));
//                mViewCount--;
//            }
//        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        mHandler.removeMessages(REFRESH);
    }
}
