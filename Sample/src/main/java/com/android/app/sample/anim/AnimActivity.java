package com.android.app.sample.anim;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationSet;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.app.sample.R;

public class AnimActivity extends AppCompatActivity {

    private LinearLayout mViewContainer;
    private int mViewCount = 0;
    private boolean isTransitionFinish = true;

    private static final int REFRESH = 0X001;

    private Handler mHandler = new Handler(){
        @Override
        public void dispatchMessage(Message msg) {
            switch (msg.what){
                case REFRESH:
                    addViewToParent(null);
                    mHandler.sendEmptyMessageDelayed(REFRESH, 3000);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anim_activity);
        mViewContainer = (LinearLayout) findViewById(R.id.view_container);
        LayoutTransition mLayoutTransition = new LayoutTransition();
        mLayoutTransition.setAnimator(LayoutTransition.APPEARING, getAppearingAnimation());
        mLayoutTransition.setDuration(LayoutTransition.APPEARING, 400);
        mLayoutTransition.setStartDelay(LayoutTransition.APPEARING, 0);//源码中带有默认300毫秒的延时，需要移除，不然view添加效果不好！！

        mLayoutTransition.setAnimator(LayoutTransition.DISAPPEARING, getDisappearingAnimation());
        mLayoutTransition.setDuration(LayoutTransition.DISAPPEARING, 400);
        mLayoutTransition.setStartDelay(LayoutTransition.DISAPPEARING, 0);
//
//        mLayoutTransition.setAnimator(LayoutTransition.CHANGE_APPEARING, getAppearingChangeAnimation());
//        mLayoutTransition.setDuration(200);
//
//        mLayoutTransition.setAnimator(LayoutTransition.CHANGE_DISAPPEARING, getDisappearingChangeAnimation());
//        mLayoutTransition.setDuration(200);
//
//        mLayoutTransition.enableTransitionType(LayoutTransition.CHANGE_DISAPPEARING);
//        mLayoutTransition.setStartDelay(LayoutTransition.CHANGE_DISAPPEARING, 0);//源码中带有默认300毫秒的延时，需要移除，不然view添加效果不好！！
        mLayoutTransition.addTransitionListener(new LayoutTransition.TransitionListener() {
            @Override
            public void startTransition(LayoutTransition transition, ViewGroup container, View view, int transitionType) {
                Log.i("zyq", "LayoutTransition:startTransition");
            }

            @Override
            public void endTransition(LayoutTransition transition, ViewGroup container, View view, int transitionType) {
                Log.i("zyq", "LayoutTransition:endTransition");
                isTransitionFinish = true;
            }
        });
        mViewContainer.setLayoutTransition(mLayoutTransition);
        mHandler.sendEmptyMessageDelayed(REFRESH, 3000);

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
        if (isTransitionFinish) {
            isTransitionFinish = false;
            if (mViewContainer.getChildCount() > 0){
                mViewContainer.removeViewAt(0);

            }
            mViewContainer.postDelayed(new Runnable() {
                @Override
                public void run() {
                    View view1 = LayoutInflater.from(AnimActivity.this).inflate(R.layout.item_adapter1, mViewContainer, false);
                    ((TextView) view1).setText("添加" + mViewCount);
                    mViewContainer.addView(view1, 0);
                    mViewCount++;
                }
            }, 400);

        }
    }

    public void removeViewFromParent(View view) {
        if (isTransitionFinish) {
            if (mViewCount >= 1) {
                isTransitionFinish = false;
                mViewContainer.removeViewAt((0));
                mViewCount--;
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeMessages(REFRESH);
    }
}
