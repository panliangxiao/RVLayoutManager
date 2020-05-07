package com.android.app.sample.suspension;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

/**
 * item修饰器用于悬浮效果
 */
public class SectionDecoration extends RecyclerView.ItemDecoration {

    private int mHeight = 15;
    private GroupListener mListener;

    private Paint mGroupPaint;
    private Paint mTextPaint;

    public SectionDecoration(int height, GroupListener listener) {
        this.mHeight = height;
        this.mListener = listener;
        mGroupPaint = new Paint();
        mGroupPaint.setColor(Color.BLUE);
        mTextPaint = new Paint();
        mTextPaint.setTextSize(40);
        mTextPaint.setColor(Color.WHITE);
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        final int itemCount = state.getItemCount();
        final int childCount = parent.getChildCount();
        final int left = parent.getLeft() + parent.getPaddingLeft();
        final int right = parent.getRight() - parent.getPaddingRight();
        String preGroupName;      //标记上一个item对应的Group
        String currentGroupName = null;       //当前item对应的Group
        for (int i = 0; i < childCount; i++) {
            View view = parent.getChildAt(i);
            int position = parent.getChildAdapterPosition(view);
            preGroupName = currentGroupName;
            currentGroupName = getGroupName(position);
            if (currentGroupName == null || TextUtils.equals(currentGroupName, preGroupName))
                continue;
            int viewBottom = view.getBottom();
            float top = Math.max(mHeight, view.getTop());//top 决定当前顶部第一个悬浮Group的位置
            if (position + 1 < itemCount) {
                //获取下个GroupName
                String nextGroupName = getGroupName(position + 1);
                //下一组的第一个View接近头部
                if (!currentGroupName.equals(nextGroupName) && viewBottom < top) {
                    top = viewBottom;
                }
            }
            //根据top绘制group
            c.drawRect(left, top - mHeight, right, top, mGroupPaint);
            Paint.FontMetrics fm = mTextPaint.getFontMetrics();
            //文字竖直居中显示
            float baseLine = top - (mHeight - (fm.bottom - fm.top)) / 2 - fm.bottom;
            c.drawText(currentGroupName, left, baseLine, mTextPaint);
        }
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        int pos = parent.getChildAdapterPosition(view);
        String pinyin = "";
        if (mListener != null) {
            pinyin = mListener.groupName(pos);
        }
        if (TextUtils.isEmpty(pinyin))
            return;
        //只有是同一组的第一个才显示悬浮栏
        if (firstInGroup(pos)) {
            outRect.top = mHeight;
        }
    }

    /**
     * 判断是不是组中的第一个位置
     * 通过对比前一个组的groupName 是否相等
     * @param pos
     * @return
     */
    private boolean firstInGroup(int pos) {
        if (pos == 0) {
            return true;
        }
        String prevGroupId = getGroupName(pos - 1);
        String groupId = getGroupName(pos);
        return !TextUtils.equals(prevGroupId, groupId);
    }

    private String getGroupName(int pos){
        if (mListener == null){
            return "";
        }
        return mListener.groupName(pos);
    }
}
