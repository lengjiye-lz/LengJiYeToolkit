package com.lengjiye.toolkit.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.lengjiye.toolkit.utils.NetworkUtils;

/**
 * 监听网络变化的广播
 * Created by lz on 2016/5/18.
 */
public class NetworkChangedReceiver extends BroadcastReceiver {

    private static NetworkChangedListener listener;

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        //Toast.makeText(context, intent.getAction(), 1).show();
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeInfo = manager.getActiveNetworkInfo();
        if (activeInfo != null) {
            if (listener != null) {
                listener.onNetworkLinkSuccess();
            }
        } else {
            if (listener != null) {
                listener.onNetworkLinkFailure();
            }
            Toast.makeText(context, "网络链接断开", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 网络变化接口
     */
    public interface NetworkChangedListener {
        void onNetworkLinkSuccess();

        void onNetworkLinkFailure();
    }

    public static void setNetworkChangedListener(NetworkChangedListener changedListener) {
        listener = changedListener;
        if (NetworkUtils.isNetworkConnected()) {
            listener.onNetworkLinkSuccess();
        } else {
            listener.onNetworkLinkFailure();
        }
    }
}
