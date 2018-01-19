package com.lengjiye.toolkit.itemdelegate;

import android.view.View;

import com.lengjiye.toolkit.R;
import com.lengjiye.toolkit.application.LJYApplication;
import com.lengjiye.tools.LogTool;
import com.lengjiye.tools.ToastTool;
import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;

/**
 * 类描述:
 * 创建人: lz
 * 创建时间: 2017/12/21
 * 修改备注:
 */

public class TextItemDelegate implements ItemViewDelegate<String> {

    public static String obj1 = "obj1";
    public static String obj2 = "obj2";

    public TextItemDelegate() {

    }

    @Override
    public int getItemViewLayoutId() {
        return R.layout.adapter_recycler_view;
    }

    @Override
    public boolean isForViewType(String item, int position) {
        return true;
    }

    @Override
    public void convert(final ViewHolder holder, String user, final int position) {
        holder.setText(R.id.text, user);
        holder.setOnClickListener(R.id.text, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastTool.getInstance().show(LJYApplication.getInstance().getApplicationContext(), "item:" + position);

                Thread a = new Thread(new Lock1());
                Thread b = new Thread(new Lock2());
                a.start();
                b.start();
            }
        });

    }

    class Lock1 implements Runnable {
        @Override
        public void run() {
            try {
                LogTool.e("Lock1 running");
                while (true) {
                    synchronized (obj1) {
                        LogTool.e("Lock1 lock obj1");
                        Thread.sleep(3000);//获取obj1后先等一会儿，让Lock2有足够的时间锁住obj2
                        synchronized (obj2) {
                            LogTool.e("Lock1 lock obj2");
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    class Lock2 implements Runnable {
        @Override
        public void run() {
            try {
                LogTool.e("Lock2 running");
                while (true) {
                    synchronized (obj2) {
                        LogTool.e("Lock2 lock obj2");
                        Thread.sleep(3000);
                        synchronized (obj1) {
                            LogTool.e("Lock2 lock obj1");
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
