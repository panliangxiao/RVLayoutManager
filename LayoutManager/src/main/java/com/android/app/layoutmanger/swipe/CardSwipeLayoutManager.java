package com.android.app.layoutmanger.swipe;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * 仿造探探卡片滑动效果CardSwipeLayoutManager
 */
public class CardSwipeLayoutManager extends RecyclerView.LayoutManager {

    public static final int DEFAULT_SHOW_ITEM = 3; //屏幕上显示的卡片数
    public static final float DEFAULT_SCALE = 0.1f;
    public static final int DEFAULT_TRANSLATE_Y = 14;

    private RecyclerView mRecyclerView;

    private ItemTouchHelper mItemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
        @Override
        public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
            return 0;
        }

        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        }
    });

    private View.OnTouchListener mOnTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (mRecyclerView == null){
                return false;
            }
            RecyclerView.ViewHolder childViewHolder = mRecyclerView.getChildViewHolder(v);
            // 把触摸事件交给 mItemTouchHelper，让其处理卡片滑动事件
            if (event.getActionMasked() == MotionEvent.ACTION_DOWN) {
                mItemTouchHelper.startSwipe(childViewHolder);
            }
            return false;
        }
    };
    /**
     * step1 生成LayoutParams
     * @return
     */
    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    /**
     * step3 获取RecyclerView
     * @return
     */
    @Override
    public void onAttachedToWindow(RecyclerView view) {
        super.onAttachedToWindow(view);
        mRecyclerView = view;
    }

    /**
     * step2 重写onLayoutChildren 实现布局
     */
    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        super.onLayoutChildren(recycler, state);
        // 将所有的子 View 先 Detach 掉，放入到 Scrap 缓存中
        detachAndScrapAttachedViews(recycler);
        int itemCount = getItemCount() > DEFAULT_SHOW_ITEM ? DEFAULT_SHOW_ITEM : getItemCount();
        // 在这里，默认配置 DEFAULT_SHOW_ITEM = 3。即在屏幕上显示的卡片数为3
        // 倒序第0个数据就在屏幕最上面 为了保持动画的连贯布局4个view
        for (int position = itemCount; position >= 0; position--){
            final View view = recycler.getViewForPosition(position);
            // 将 View 加入到 RecyclerView 中
            addView(view);
            // 测量 View
            measureChildWithMargins(view, 0, 0);
            // 获取 View 的宽度
            // 计算显示字view后屏幕剩余宽度widthSpace
            int widthSpace = getWidth() - getDecoratedMeasuredWidth(view);
            // 同理
            int heightSpace = getHeight() - getDecoratedMeasuredHeight(view);
            // 设置view layout参数 left，right，top，bottom值放置在屏幕中央
            layoutDecoratedWithMargins(view, widthSpace / 2, heightSpace / 2,
                    widthSpace / 2 + getDecoratedMeasuredWidth(view),
                    heightSpace / 2 + getDecoratedMeasuredHeight(view));

            // 第四张卡片和第三张重叠
            if (position >= DEFAULT_SHOW_ITEM) {
                // 按照规则缩放，并且偏移Y轴。
                view.setScaleX(1 - (position - 1) * DEFAULT_SCALE);
                view.setScaleY(1 - (position - 1) * DEFAULT_SCALE);
                view.setTranslationY((float) (position - 1) * view.getMeasuredHeight() / DEFAULT_TRANSLATE_Y);
            } else if (position > 0) {
                view.setScaleX(1 - position * DEFAULT_SCALE);
                view.setScaleY(1 - position * DEFAULT_SCALE);
                view.setTranslationY((float) position * view.getMeasuredHeight() / DEFAULT_TRANSLATE_Y);
            } else {
                //最顶层view设置OnTouchListener监听动作
                view.setOnTouchListener(mOnTouchListener);
            }
        }
    }
}
