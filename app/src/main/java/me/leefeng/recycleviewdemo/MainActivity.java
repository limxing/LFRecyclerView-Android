package me.leefeng.recycleviewdemo;

import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

import me.leefeng.lfrecycleview.LFRecyclerView;
import me.leefeng.lfrecycleview.OnItemClickListener;


/**
 * Created by limxing on 16/7/23.
 * <p/>
 * https://github.com/limxing
 * Blog: http://www.leefeng.me
 */
public class MainActivity extends AppCompatActivity implements OnItemClickListener, LFRecyclerView.LFRecyclerViewListener, LFRecyclerView.LFRecyclerViewScrollChange {
    private LFRecyclerView recycleview;
    private boolean b;
    private ArrayList<String> list;
    private MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            list.add("leefeng.me" + i);
        }

        recycleview = (LFRecyclerView) findViewById(R.id.recycleview);
        recycleview.setLoadMore(true);
        recycleview.setRefresh(true);
        recycleview.setNoDateShow();
        recycleview.setAutoLoadMore(true);
        recycleview.setOnItemClickListener(this);
        recycleview.setLFRecyclerViewListener(this);
        recycleview.setScrollChangeListener(this);
        recycleview.setItemAnimator(new DefaultItemAnimator());
        adapter = new MainAdapter(list);
        recycleview.setAdapter(adapter);

        TextView tv = new TextView(MainActivity.this);
        tv.setText("这是头部");
        tv.setTextColor(Color.WHITE);
        tv.setGravity(Gravity.CENTER);
        tv.setBackgroundColor(Color.RED);
        recycleview.setHeaderView(tv);
    }

    @Override
    public void onClick(int position) {
        Toast.makeText(this, "" + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLongClick(int po) {
        Toast.makeText(this, "Long:" + po, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                b = !b;
                list.add(0, "leefeng.me" + "==onRefresh");
                recycleview.stopRefresh(b);
                adapter.notifyItemInserted(0);
                adapter.notifyItemRangeChanged(0,list.size());

            }
        }, 2000);
    }

    @Override
    public void onLoadMore() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                recycleview.stopLoadMore();
                list.add(list.size(), "leefeng.me" + "==onLoadMore");
                list.add(list.size(), "leefeng.me" + "==onLoadMore");
                adapter.notifyItemRangeInserted(list.size()-1,2);

            }
        }, 2000);
    }

    @Override
    public void onRecyclerViewScrollChange(View view, int i, int i1) {

    }
}
