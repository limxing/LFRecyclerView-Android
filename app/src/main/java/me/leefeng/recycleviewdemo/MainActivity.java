package me.leefeng.recycleviewdemo;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import me.leefeng.lfrecycleview.LFRecyclerView;
import me.leefeng.lfrecycleview.OnItemClickListener;


/**
 * Created by limxing on 16/7/23.
 *
 * https://github.com/limxing
 * Blog: http://www.leefeng.me
 */
public class MainActivity extends AppCompatActivity implements OnItemClickListener, LFRecyclerView.LFRecyclerViewListener {

    private LFRecyclerView recycleview;
    private boolean b;
    private ArrayList<String> list;
    private MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list=new ArrayList<String>();
        for (int i=0;i<20;i++){
            list.add("leefeng.me"+i);
        }

        recycleview = (LFRecyclerView) findViewById(R.id.recycleview);
        recycleview.setLoadMore(true);
        recycleview.setOnItemClickListener(this);
        recycleview.setLFRecyclerViewListener(this);

        adapter=new MainAdapter(list);
        recycleview.setAdapter(adapter);

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
                recycleview.stopRefresh(b);
                b = !b;
                list.add(0,"leefeng.me"+"==onRefresh");
            }
        }, 2000);
    }

    @Override
    public void onLoadMore() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                recycleview.stopLoadMore();
                list.add(list.size(),"leefeng.me"+"==onLoadMore");
            }
        }, 2000);
    }
}
