package me.leefeng.lfrecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by limxing on 16/7/23.
 *
 * https://github.com/limxing
 * Blog: http://www.leefeng.me
 */
public class LFRecyclerViewAdapter extends RecyclerView.Adapter {

    //item类型
    public static final int ITEM_TYPE_HEADER = 0;
    public static final int ITEM_TYPE_CONTENT = 1;
    public static final int ITEM_TYPE_BOTTOM = 2;
    public static final int ITEM_TYPE_HEADERVIEW = 3;
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

    private View headerView;
    public int itemHeight;

    public void setHeaderView(View headerView) {
        this.headerView = headerView;
    }

    public void setRecyclerViewFooter(LFRecyclerViewFooter recyclerViewFooter) {
        this.recyclerViewFooter = recyclerViewFooter;
    }

    public void setRecyclerViewHeader(LFRecyclerViewHeader recyclerViewHeader) {
        this.recyclerViewHeader = recyclerViewHeader;
    }

    public void setRefresh(boolean refresh) {
        isRefresh = refresh;
        if (refresh) {
            mHeaderCount = 1;
        } else {
            mHeaderCount = 0;
        }
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
        } else if (viewType == ITEM_TYPE_CONTENT) {
            return adapter.onCreateViewHolder(parent, viewType);
        } else if (viewType == ITEM_TYPE_BOTTOM) {
            return new HeaderBottomHolder(recyclerViewFooter);
        } else if (viewType == ITEM_TYPE_HEADERVIEW) {

            return new HeaderBottomHolder(headerView);
        }
        return adapter.onCreateViewHolder(parent, viewType);
    }

    public void setOnItemClickListener(OnItemClickListener itemListener) {
        this.itemListener = itemListener;
    }

    public int getHFCount() {
        return getheaderViewCount()+ mBottomCount;
    }


    class HeaderBottomHolder extends ViewHolder {

        public HeaderBottomHolder(View itemView) {
            super(itemView);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (isHeaderView(position) || isBottomView(position) || isCustomHeaderView(position)) {
            return;
        }
        final int po = position - getheaderViewCount();
        adapter.onBindViewHolder(holder, po);
        if (itemHeight==0){
            itemHeight=holder.itemView.getHeight();

        }
        if (itemListener != null) {
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
    }

    @Override
    public int getItemCount() {
        int count = adapter.getItemCount();
        count = count + getheaderViewCount();
//        if (isLoadMore) {
            count = count + mBottomCount;
//        }
        return count;
    }


    @Override
    public int getItemViewType(int position) {

        if (isHeaderView(position) && isRefresh) {
            //头部View
            return ITEM_TYPE_HEADER;
        } else if (isBottomView(position)) {
            //底部View
            return ITEM_TYPE_BOTTOM;
        } else if (isCustomHeaderView(position)) {
            //内容View
            return ITEM_TYPE_HEADERVIEW;
        } else {
            return ITEM_TYPE_CONTENT;
        }
    }


    //判断当前item是否是HeadView
    public boolean isHeaderView(int position) {
        return mHeaderCount != 0 && position < mBottomCount;
    }

    //判断当前item是否是FooterView
    public boolean isBottomView(int position) {
        return mBottomCount != 0 && position >= (getheaderViewCount() + adapter.getItemCount());
    }

    //判断当前是否是自定义头部View
    public boolean isCustomHeaderView(int position) {
        return headerView != null && position == mHeaderCount;
    }

    public int getheaderViewCount() {
        int count = 0;
        if (isRefresh) {
            count += 1;
        }
        if (headerView != null) {
            count += 1;
        }
        return count;
    }
}
