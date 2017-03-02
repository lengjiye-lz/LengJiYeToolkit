package com.lengjiye.toolkit;

import android.test.AndroidTestCase;
import android.util.Log;

import com.lengjiye.toolkit.utils.FileUtils;

import java.io.File;

/**
 * 方法测试
 * Created by lz on 2016/4/29.
 */
public class StringTest extends AndroidTestCase {

    /**
     * 冒泡排序
     */
    public void testBubbleSort() {
        int a[] = {3, 6, 2, 2, 11, 2, 5};
        int temp;
        int k = 0;
        for (int i = a.length - 1; i > 0; --i) {
            for (int j = 0; j < i; ++j) {
                if (a[j + 1] < a[j]) {
                    temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
                k++;
            }
        }
        Log.e("lz", "a:" + a);
        Log.e("lz", "k:" + k);
    }

    public void testBubbleSort1() {
        int a[] = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        int temp;
        int k = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = i; j < a.length; j++) {
                if (a[i] > a[j]) {
                    temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                }
                k++;
            }
        }
        Log.e("lz", "a:" + a);
        Log.e("lz", "k:" + k);
    }

    /**
     * 插入排序法
     */
    public void testChaRu() {
        int a[] = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        for (int i = 1; i < a.length; i++) {
            for (int j = i; j > 0; j--) {
                if (a[j] < a[j - 1]) {
                    int temp = a[j];
                    a[j] = a[j - 1];
                    a[j - 1] = temp;
                }
            }
        }
    }

    public void testGetFile() {
        if (FileUtils.isExist(FileUtils.getSdCardPath())) {
            File file = new File(FileUtils.getSdCardPath());
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                Log.e("lz", "files:" + files[i]);
                if (!files[i].isDirectory()){
                    Log.e("lz", "files222:" + files[i]);
                }
            }
        }
    }
}
