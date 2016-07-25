package me.leefeng.lfrecycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by limxing on 16/7/23.
 * <p/>
 * https://github.com/limxing
 * Blog: http://www.leefeng.me
 */
public class LFRecyclerViewAdapter extends RecyclerView.Adapter {

    //item类型
    public static final int ITEM_TYPE_HEADER = 0;
    public static final int ITEM_TYPE_CONTENT = 1;
    public static final int ITEM_TYPE_BOTTOM = 2;
    //    private TextView view;
    private RecyclerView.Adapter adapter;

    //模拟数据
    protected Context mContext;
    public int mHeaderCount = 1;//头部View个数
    public int mBottomCount = 1;//底部View个数

    private boolean isLoadMore;
    private boolean isRefresh = true;
    private OnItemClickListener itemListener;
    private LFRecyclerViewFooter recyclerViewFooter;
    private LFRecyclerViewHeader recyclerViewHeader;

    public void setRecyclerViewFooter(LFRecyclerViewFooter recyclerViewFooter) {
        this.recyclerViewFooter = recyclerViewFooter;
    }

    public void setRecyclerViewHeader(LFRecyclerViewHeader recyclerViewHeader) {
        this.recyclerViewHeader = recyclerViewHeader;
    }

    public void setRefresh(boolean refresh) {
        isRefresh = refresh;
    }

    public void setLoadMore(boolean loadMore) {
        isLoadMore = loadMore;
    }

    public LFRecyclerViewAdapter(Context context, RecyclerView.Adapter adapter) {
        mContext = context;
        this.adapter = adapter;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE_HEADER) {
            return new HeaderBottomHolder(recyclerViewHeader);
        } else if (viewType == mHeaderCount) {
            return adapter.onCreateViewHolder(parent, viewType);
        } else if (viewType == ITEM_TYPE_BOTTOM) {
            return new HeaderBottomHolder(recyclerViewFooter);
        }
        return adapter.onCreateViewHolder(parent, viewType);
    }

    public void setOnItemClickListener(OnItemClickListener itemListener) {
        this.itemListener = itemListener;
    }

    class HeaderBottomHolder extends ViewHolder {

        public HeaderBottomHolder(View itemView) {
            super(itemView);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (isHeaderView(position) || isBottomView(position)) {
            return;
        }
        final int po = position - mHeaderCount;
        adapter.onBindViewHolder(holder, po);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemListener.onClick(po);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                itemListener.onLongClick(po);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        int count = adapter.getItemCount();
        if (isRefresh) {
            count = count + mHeaderCount;
        }
        if (isLoadMore) {
            count = count + mBottomCount;
        }
        return count;
    }


    @Override
    public int getItemViewType(int position) {
        if (isHeaderView(position) && isRefresh) {
            //头部View
            return ITEM_TYPE_HEADER;
        } else if (isBottomView(position) && isLoadMore) {
            //底部View
            return ITEM_TYPE_BOTTOM;
        } else {
            //内容View
            return ITEM_TYPE_CONTENT;
        }
    }


    //判断当前item是否是HeadView
    public boolean isHeaderView(int position) {
        return mHeaderCount != 0 && position < mHeaderCount;
    }

    //判断当前item是否是FooterView
    public boolean isBottomView(int position) {
        return mBottomCount != 0 && position >= (mHeaderCount + adapter.getItemCount());
    }
}
