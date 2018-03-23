package com.lengjiye.toolkit;

import android.test.AndroidTestCase;
import android.util.Log;

import com.lengjiye.tools.LogTool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Java基础测试
 * 创建人: lz
 * 创建时间: 2018/3/1
 * 修改备注:
 */

public class JavaTest extends AndroidTestCase {

    public void testInstanceof() {
        String s = "c";
        Log.e("lz", "s:" + (s instanceof String));
        Log.e("lz", "null:" + (null instanceof String));
        String i = null;
        Log.e("lz", "i:" + (i instanceof String));
    }

    /**
     * 测试runable接口
     */
    public void testRunable() {
        // 创建实现Runnable接口的类RunnableImpl
        Runnable runnable = new RunnableImpl("测试Runnable");
        // 创建线程,并启动
        new Thread(runnable).start();
    }

    /**
     * 实现Runnable接口
     */
    class RunnableImpl implements Runnable {
        // 消息
        private String msg;

        public RunnableImpl(String str) {
            this.msg = str;
        }

        @Override
        public void run() {
            LogTool.e("lz", "输出msg：" + msg);
        }
    }

    /**
     * 测试Callable接口
     */
    public void testCallable() {
        long time1 = System.currentTimeMillis();
        // 创建实现Callable接口的类CallableImpl
        Callable<Integer> callable = new CallableImpl("测试Callable");
        //FutureTask<String>是一个包装器，它通过接受Callable<String>来创建，
        // 它同时实现了Future和Runnable接口，他的run方法中实际上会调用callable.call()。
        FutureTask<Integer> task = new FutureTask<Integer>(callable);
        // 创建线程,并启动
        new Thread(task).start();
        LogTool.e("lz", "time:" + (System.currentTimeMillis() - time1));
        try {
            // 调用get()阻塞主线程，反之，线程不会阻塞
            int result = task.get();
            LogTool.e("lz", "result:" + result + " time:" + (System.currentTimeMillis() - time1));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * 实现Callable接口
     */
    class CallableImpl implements Callable<Integer> {
        // 消息
        private String msg;

        public CallableImpl(String str) {
            this.msg = str;
        }

        @Override
        public Integer call() throws Exception {
            // 模拟网络延迟
            Thread.sleep(1000);
            LogTool.e("lz", "输出msg：" + msg);
            return 1;
        }
    }

    /**
     * 测试yield()方法
     */
    public void testYield() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 50; i++) {
                    LogTool.e("lz", "线程1");
                    Thread.yield();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 50; i++) {
                    LogTool.e("lz", "线程2");
                }
            }
        }).start();
    }

    /**
     * 测试yield()方法
     */
    public void testJoin() {
        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 50; i++) {
                    LogTool.e("lz", "线程1");
                }
            }
        });
        thread.start();
        try {
            thread.join(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i < 50; i++) {
                    LogTool.e("lz", "线程2");
                }
            }
        }).start();
    }


}
