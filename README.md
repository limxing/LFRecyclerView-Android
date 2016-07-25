# LFRecycleView-Android
RecycleView的下拉刷新,上拉加载
欢迎大家尝试使用!





使用和ListView一样
```
recycleview = (LFRecyclerView) findViewById(R.id.recycleview);
recycleview.setLoadMore(true);//设置为可上拉加载
recycleview.setOnItemClickListener(this);// 条目点击
recycleview.setLFRecyclerViewListener(this);//下拉刷新上拉加载监听
recycleview.hideTimeView();//隐藏时间
adapter=new MainAdapter(list);
recycleview.setAdapter(adapter);

```



