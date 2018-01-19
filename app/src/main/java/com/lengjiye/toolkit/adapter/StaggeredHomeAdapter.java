package com.lengjiye.toolkit.adapter;

import android.content.Context;

import com.lengjiye.toolkit.itemdelegate.TextItemDelegate;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.List;

public class StaggeredHomeAdapter extends MultiItemTypeAdapter<String> {

    public StaggeredHomeAdapter(Context context, List<String> datas) {
        super(context, datas);
        addItemViewDelegate(new TextItemDelegate());
    }
}