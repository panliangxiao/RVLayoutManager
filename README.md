# RVLayoutManager
## 滚轮效果时间选择器PickerLayoutManager
### 特点
- 支持指定滚轮固定个数显示
```java
mPickerLayoutManager2 = new PickerLayoutManager(this, PickerLayoutManager.VERTICAL, false,3,0.4f,true);
mRecyclerView2.setLayoutManager(mPickerLayoutManager2);
mRecyclerView2.setAdapter(new MyAdapter(mMinutes));
```
- 支持填充RecyclerView大小显示滚轮
```java
mPickerLayoutManager2 = new PickerLayoutManager(this, PickerLayoutManager.VERTICAL, false,0,0.4f,true);
mRecyclerView2.setLayoutManager(mPickerLayoutManager2);
mRecyclerView2.setAdapter(new MyAdapter(mMinutes));
mRecyclerView2.scrollToPosition(0);
```
### 效果
- 固定个数
<img src="https://pic5.58cdn.com.cn/nowater/fangfe/n_v2c024f10fb4584ad5a85c189eb843b1cb.jpg" width = "216" height = "384" alt="时间选择器样式"
align=center>
- 自动填充
<img src="https://pic8.58cdn.com.cn/nowater/fangfe/n_v246795d437d954191849a7101383b4237.jpg" width = "216" height = "384" alt="时间选择器样式"
align=center>
## 仿探探card效果CardSwipeLayoutManager
### 特点
- 精简api 只需一个LayoutManager搞定
### 使用
```java
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

## 画廊效果GalleryLayoutManager

### 效果图
<img src="https://pic1.58cdn.com.cn/nowater/fangfe/n_v2383c9f73edb5481eb986fd4a44fc35f4.png" width = "216" height = "384" alt="画廊效果"
align=center>