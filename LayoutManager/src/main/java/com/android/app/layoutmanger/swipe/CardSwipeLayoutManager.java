package com.android.app.layoutmanger.swipe;

import android.graphics.Canvas;
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
    public static final float DEFAULT_ROTATE_DEGREE = 15f; //默认最大旋转角度
    public static final int SWIPING_NONE = 1;
    public static final int SWIPING_LEFT = 1 << 2;
    public static final int SWIPING_RIGHT = 1 << 3;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;

    /**
     * 手势监听器
     */
    private OnCardSwipeListener mListener;

    public CardSwipeLayoutManager(OnCardSwipeListener listener) {
        mListener = listener;
    }

    /**
     * step4 处理view的滑动
     */
    private ItemTouchHelper mItemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
        @Override
        public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
            int dragFlags = 0;
            int swipeFlags = 0;
            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            if (layoutManager instanceof CardSwipeLayoutManager) {
                //只关心左右两个方向
                swipeFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
            }
            return makeMovementFlags(dragFlags, swipeFlags);
        }

        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            // 移除之前清空view的 onTouchListener, 否则触摸滑动会乱
            viewHolder.itemView.setOnTouchListener(null);
            // 删除相对应的数据
            int layoutPosition = viewHolder.getLayoutPosition();
            // 卡片滑出后回调监听器
            if (mListener != null) {
                mListener.onSwiped(viewHolder, layoutPosition, direction);
            }
            // 当没有数据时回调监听器
            if (mAdapter.getItemCount() == 0) {
                if (mListener != null) {
                    mListener.onSwipedClear();
                }
            }
            mAdapter.notifyDataSetChanged();

        }

        /**
         * 只允许顶层滑动
         * @return
         */
        @Override
        public boolean isItemViewSwipeEnabled() {
            return false;
        }

        @Override
        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            // 滑动移除动画
            View itemView = viewHolder.itemView;
            if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                // 得到滑动的阀值
                float ratio = dX / getThreshold(recyclerView, viewHolder);
                // ratio 最大为 1 或 -1
                if (ratio > 1) {
                    ratio = 1;
                } else if (ratio < -1) {
                    ratio = -1;
                }
                // 默认最大的旋转角度为 15 度
                itemView.setRotation(ratio * DEFAULT_ROTATE_DEGREE);
                int childCount = recyclerView.getChildCount();
                // 当数据源个数大于最大显示数时 从1开始 当数据源个数小于或等于最大显示数时 0开始
                // 只做<=3个视图的动画
                int startPosition = recyclerView.getChildCount() > DEFAULT_SHOW_ITEM ? 1 : 0;

                for (int position = startPosition; position < childCount - 1; position++) {
                    int index = childCount - position - 1;
                    View view = recyclerView.getChildAt(position);
                    // 和之前 onLayoutChildren 是一个意思，不过是做相反的动画
                    view.setScaleX(1 - index * DEFAULT_SCALE + Math.abs(ratio) * DEFAULT_SCALE);
                    view.setScaleY(1 - index * DEFAULT_SCALE + Math.abs(ratio) * DEFAULT_SCALE);
                    view.setTranslationY((index - Math.abs(ratio)) * itemView.getMeasuredHeight() / DEFAULT_TRANSLATE_Y);
                }
                // 回调监听器
                if (mListener != null) {
                    if (ratio != 0) {
                        mListener.onSwiping(viewHolder, ratio, ratio < 0 ? SWIPING_LEFT : SWIPING_RIGHT);
                    } else {
                        mListener.onSwiping(viewHolder, ratio, SWIPING_NONE);
                    }
                }
            }
        }

        @Override
        public void clearView(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
            super.clearView(recyclerView, viewHolder);
            //移除是设置旋转角度0 防止view复用角度问题
            viewHolder.itemView.setRotation(0f);
        }

        private float getThreshold(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            return recyclerView.getWidth() * getSwipeThreshold(viewHolder);
        }
    });

    private View.OnTouchListener mOnTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (mRecyclerView == null) {
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
     *
     * @return
     */
    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    /**
     * step3 获取RecyclerView
     *
     * @return
     */
    @Override
    public void onAttachedToWindow(RecyclerView view) {
        super.onAttachedToWindow(view);
        mRecyclerView = view;
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
        mAdapter = mRecyclerView.getAdapter();
    }

    /**
     * step2 重写onLayoutChildren 实现布局
     */
    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        super.onLayoutChildren(recycler, state);
        // 将所有的子 View 先 Detach 掉，放入到 Scrap 缓存中
        detachAndScrapAttachedViews(recycler);
        int itemCount = getItemCount();
        // 在这里，默认配置 DEFAULT_SHOW_ITEM = 3。即在屏幕上显示的卡片数为3
        // 倒序第0个数据就在屏幕最上面 为了保持动画的连贯布局4个view
        int position = itemCount > DEFAULT_SHOW_ITEM ? DEFAULT_SHOW_ITEM : itemCount - 1;
        for (; position >= 0; position--) {
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
