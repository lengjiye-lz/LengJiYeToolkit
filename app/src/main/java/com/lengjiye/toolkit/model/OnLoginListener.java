package com.lengjiye.toolkit.model;

import com.lengjiye.toolkit.bean.User;

/**
 * Created by liuz on 2017/9/20.
 */

public interface OnLoginListener {
    void onSuccess(User user);

    void onFailed();
}
