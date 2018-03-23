package com.lengjiye.toolkit.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lengjiye.toolkit.R;
import com.lengjiye.toolkit.model.TestData;
import com.lengjiye.toolkit.view.ChartLayout;

import java.util.List;

/**
 * 适配器
 * Created by lz on 2016/4/21.
 */
public class RecyclerViewTestAdapter extends RecyclerView.Adapter<RecyclerViewTestAdapter.MyViewHolder> {

    private List<TestData> mDatas;
    private LayoutInflater mInflater;

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }


    public RecyclerViewTestAdapter(Context context, List<TestData> datas) {
        mInflater = LayoutInflater.from(context);
        mDatas = datas;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // TODO 使用parent可以使单个item充满屏幕，使用null不会充满屏幕
        MyViewHolder holder = new MyViewHolder(mInflater.inflate(
                R.layout.adapter_recycler_test_view, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        TestData testData = mDatas.get(position);
        holder.chartLayout.setData(testData.getList1(), testData.getList2());

        // 如果设置了回调，则设置点击事件
        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView, pos);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemLongClick(holder.itemView, pos);
                    removeData(pos);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public void addData(int position) {
//        mDatas.add(position, "Insert One");
        notifyItemInserted(position);
    }


    public void removeData(int position) {
        mDatas.remove(position);
        notifyItemRemoved(position);
    }

    class MyViewHolder extends ViewHolder {

        ChartLayout chartLayout;

        public MyViewHolder(View view) {
            super(view);
            chartLayout = (ChartLayout) view.findViewById(R.id.chart);
        }
    }
}