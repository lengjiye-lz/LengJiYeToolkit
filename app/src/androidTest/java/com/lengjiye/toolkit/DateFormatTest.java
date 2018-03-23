package com.lengjiye.toolkit;

import android.test.AndroidTestCase;


import com.lengjiye.tools.DateFormatTool;
import com.lengjiye.tools.LogTool;
import com.lengjiye.tools.TimeTool;

import java.util.Date;

/**
 * 类描述:
 * 创建人: lz
 * 创建时间: 2016/12/15
 * 修改备注:
 */
public class DateFormatTest extends AndroidTestCase {

    public void testFormatDate1() {
        String time = DateFormatTool.formatDate1(new Date());
        LogTool.e("time:" + time);
    }

    public void testGetMonth() {
        int monday = TimeTool.getWeek();
        LogTool.e("monday:" + monday);
    }

    public void testGetYear() {
        int monday = TimeTool.getYear();
        LogTool.e("monday:" + monday);
    }

    public void testGetDay() {
        int monday = TimeTool.getDay();
        LogTool.e("monday:" + monday);
    }
}
