package com.lengjiye.toolkit.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lengjiye.toolkit.R;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * 类说明
 * Created by lz on 2016/4/21.
 */
public class MainAdapter extends BaseAdapter {

    private Context mContext;
    private List<String> strings;

    public MainAdapter(Context mContext, List<String> strings) {
        this.mContext = mContext;
        this.strings = strings;
    }

    @Override
    public int getCount() {
        return strings.size();
    }

    @Override
    public Object getItem(int position) {
        return strings.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = View.inflate(mContext, R.layout.adapter_main, null);
            x.view().inject(viewHolder, convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.text.setText(strings.get(position));
        return convertView;
    }

    private class ViewHolder {
        @ViewInject(R.id.text)
        private TextView text;
    }
}
