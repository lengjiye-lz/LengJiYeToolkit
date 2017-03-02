package com.lengjiye.toolkit;

import android.test.AndroidTestCase;

import com.lengjiye.toolkit.utils.DateFormatUtils;
import com.lengjiye.toolkit.utils.LogUtils;
import com.lengjiye.toolkit.utils.TimeUtils;

import java.util.Date;

/**
 * 类描述:
 * 创建人: lz
 * 创建时间: 2016/12/15
 * 修改备注:
 */
public class DateFormatTest extends AndroidTestCase {

    public void testFormatDate1() {
        String time = DateFormatUtils.formatDate1(new Date());
        LogUtils.e("time:" + time);
    }

    public void testGetMonth() {
        int monday = TimeUtils.getWeek();
        LogUtils.e("monday:" + monday);
    }

    public void testGetYear() {
        int monday = TimeUtils.getYear();
        LogUtils.e("monday:" + monday);
    }

    public void testGetDay() {
        int monday = TimeUtils.getDay();
        LogUtils.e("monday:" + monday);
    }
}
