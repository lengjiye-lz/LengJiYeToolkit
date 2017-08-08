package com.lengjiye.toolkit.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.vlayout.RecyclablePagerAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.lengjiye.toolkit.R;

/**
 * 类描述:
 * 创建人: lz
 * 创建时间: 2017/4/5
 * 修改备注:
 */
public class PagerAdapter extends RecyclablePagerAdapter<SubAdapter.MainViewHolder> {

    public PagerAdapter(SubAdapter adapter, RecyclerView.RecycledViewPool pool) {
        super(adapter, pool);
    }

    @Override
    public int getCount() {
        return 6;
    }

    @Override
    public void onBindViewHolder(SubAdapter.MainViewHolder viewHolder, int position) {
        // only vertical
        viewHolder.itemView.setLayoutParams(
                new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        ((TextView) viewHolder.itemView.findViewById(R.id.title)).setText("Banner: " + position);
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }
}
