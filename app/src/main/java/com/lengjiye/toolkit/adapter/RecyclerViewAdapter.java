package com.lengjiye.toolkit.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lengjiye.toolkit.R;

import java.util.List;

/**
 * 适配器
 * Created by lz on 2016/4/21.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewViewHolder> {

    private Context mContext;
    private List<String> strings;

    public RecyclerViewAdapter(Context mContext, List<String> strings) {
        this.mContext = mContext;
        this.strings = strings;
    }

    /**
     * 创建ViewHolder
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public RecyclerViewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerViewViewHolder recyclerViewViewHolder = new RecyclerViewViewHolder(
                LayoutInflater.from(mContext).inflate(R.layout.adapter_recycler_view, parent, false));
        return recyclerViewViewHolder;
    }

    /**
     * 设置值
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(RecyclerViewViewHolder holder, int position) {
        holder.text.setText(strings.get(position));
    }

    @Override
    public int getItemCount() {
        return strings.size();
    }


    public class RecyclerViewViewHolder extends RecyclerView.ViewHolder {
        private TextView text;

        public RecyclerViewViewHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.text);
        }
    }
}
