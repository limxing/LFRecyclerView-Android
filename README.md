# LFRecyclerView-Android
RecyclerView的下拉刷新,上拉加载,也不需要关心点击长按事件

欢迎大家尝试使用!

<img src="http://www.leefeng.me/GIF.gif"/>



使用和ListView一样
```
recycleview = (LFRecyclerView) findViewById(R.id.recycleview);
recycleview.setLoadMore(true);//设置为可上拉加载
recycleview.setOnItemClickListener(this);// 条目点击,点击和长按监听
recycleview.setLFRecyclerViewListener(this);//下拉刷新上拉加载监听
recycleview.hideTimeView();//隐藏时间
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


