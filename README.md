# LFRecyclerView-Android
RecyclerView的下拉刷新,上拉加载,也不需要关心点击长按事件

欢迎大家尝试使用!欢迎提BUG

<img src="http://www.leefeng.me/GIF.gif"/>
<img src="http://www.leefeng.me/leefeng1.jpg"/>



使用和ListView一样 [![](https://jitpack.io/v/limxing/LFRecyclerView-Android.svg)](https://jitpack.io/#limxing/LFRecyclerView-Android)

```

引入
//compile 'me.leefeng:lfrecyclerview:1.0.4'
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
}
	dependencies {
    	        compile 'com.github.limxing:LFRecyclerView-Android:1.0.2'
    }
    	
/*找到控件*/
recycleview = (LFRecyclerView) findViewById(R.id.recycleview);

/*设置属性*/
recycleview.setLoadMore(true);//设置为可上拉加载,默认false,调用这个方法false可以去掉底部的“加载更多”
 recycleview.setRefresh(true);// 设置为可下拉刷新,默认true
recycleview.setAutoLoadMore(true);//设置滑动到底部自动加载,默认false
recycleview.setOnItemClickListener(this);// 条目点击,点击和长按监听
recycleview.setLFRecyclerViewListener(this);//下拉刷新上拉加载监听
recycleview.setScrollChangeListener(this);//滑动监听
recycleview.hideTimeView();//隐藏时间,默认显示时间
recycleview.setHeaderView(tv);//设置一个头部,只有一个大概满足了多数的要求
 recycleview.setFootView(tv);//设置一个底部
 recycleview.setNoDateShow();//没有数据时,底部显示"没有数据"字样,默认不显示

/*添加适配器*/
adapter=new MainAdapter(list);
recycleview.setAdapter(adapter);

```


License

```
Copyright 2016 leefeng.me

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

```


