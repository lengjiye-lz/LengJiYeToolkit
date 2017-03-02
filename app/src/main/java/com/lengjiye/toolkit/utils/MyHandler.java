package com.lengjiye.toolkit.utils;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/**
 * Handler的操作类
 * Created by lz on 2016/7/27.
 */
public class MyHandler extends Handler {

    private IHandler iHandler;

    public MyHandler(Looper looper) {
        super(looper);
    }

    public MyHandler(Looper looper, IHandler iHandler) {
        super(looper);
        this.iHandler = iHandler;
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        if (iHandler != null) {
            iHandler.handleMessage(msg);
        }
    }

    public void setHandler(IHandler iHandler) {
        this.iHandler = iHandler;
    }

    public interface IHandler {
        void handleMessage(Message msg);
    }
}
