# RVLayoutManager
## 滚轮效果时间选择器PickerLayoutManager
<img src="https://pic5.58cdn.com.cn/nowater/fangfe/n_v2c024f10fb4584ad5a85c189eb843b1cb.jpg" width = "216" height = "384" alt="时间选择器样式"
align=center>
## 仿探探card效果CardSwipeLayoutManager
### 特点
- 精简api 只需一个LayoutManager搞定
### 使用
```
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
        addData();
    }
});
mRecyclerView.setLayoutManager(swipeLayoutManager);
mRecyclerView.setAdapter(mAdapter);
```