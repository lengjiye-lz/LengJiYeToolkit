package com.lengjiye.toolkit.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lengjiye.toolkit.R;
import com.lengjiye.toolkit.bean.User;
import com.lengjiye.toolkit.model.Day;
import com.lengjiye.tools.LogTool;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * WebView
 * Created by lz on 2016/7/19.
 */
public class ThreadFragment extends BaseFragment {

    private Unbinder unbinder;

    public ThreadFragment() {
    }

    /**
     * 创建一个fragment
     *
     * @return
     */
    public static ThreadFragment newInstance() {
        ThreadFragment fragment = new ThreadFragment();
        return fragment;
    }

    @Override
    public View createView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thread, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void initData() {
        super.initData();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null) {
            unbinder.unbind();
            unbinder = null;
        }
    }

    @OnClick({R.id.button1, R.id.button2, R.id.button3, R.id.button4, R.id.button5})
    public void buttonClick(View view) {
        switch (view.getId()) {
            case R.id.button1:
                testInterrupte();
                break;
            case R.id.button2:
                testCyclicBarrier();
                break;
            case R.id.button3:
                testCountDownLatch();
                break;
            case R.id.button4:
                testAddMinus();
                break;
            case R.id.button5:
//                testEnum(Day.FRIDAY);
                getClassData();
                break;
            default:
                break;
        }
    }


    /**
     * 测试中断方法
     */
    public void testInterrupte() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                LogTool.e("lz", "开始循环111111111111");
                try {
                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                LogTool.e("lz", "自线程开始休眠");
                                Thread.sleep(1000);
                                LogTool.e("lz", "自线程开始休眠结束");
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                                LogTool.e("lz", "自线程开始休眠结束：" + e + " isInterrupted:" + Thread.currentThread().isInterrupted());
                            }
                        }
                    });
                    thread.start();
                    Thread.sleep(100);
                    thread.interrupt();
                    thread.isInterrupted();
                    Thread.interrupted();
                    LogTool.e("lz", "中断线程");
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    /**
     * 测试CyclicBarrier
     */
    private void testCyclicBarrier() {
        // 参数表示屏障拦截的线程数量，每个线程调用await方法告诉CyclicBarrier我已经到达了屏障，然后当前线程被阻塞。
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                LogTool.e("lz", "测试屏障1");
            }
        }).start();
        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        LogTool.e("lz", "测试屏障2");
    }

    /**
     * 测试CountDownLatch
     */
    private void testCountDownLatch() {
        // 参数上就是闭锁需要等待的线程数量。这个值只能被设置一次，而且CountDownLatch没有提供任何机制去重新设置这个计数值。
        final CountDownLatch countDownLatch = new CountDownLatch(4);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
                LogTool.e("lz", "ceshi1:" + countDownLatch.getCount());
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
                LogTool.e("lz", "ceshi2:" + countDownLatch.getCount());
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(800);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
                LogTool.e("lz", "ceshi3:" + countDownLatch.getCount());
            }
        }).start();

        try {
            countDownLatch.await();
            LogTool.e("lz", "剩余阻塞数量:" + countDownLatch.getCount());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试自增
     */
    private void testAddMinus() {
        int a = 5;
        int b = 5;
        int c = 5;
        int d = 5;
        // 先自增在运算
        int x = 2 * ++a;
        int z = 2 * --b;
        // 先运算在自增
        int y = 2 * c++;
        int h = 2 * d--;
        LogTool.e("x:" + x + " ++a:" + a);
        LogTool.e("z:" + z + " --b:" + b);
        LogTool.e("y:" + y + " c++:" + c);
        LogTool.e("h:" + h + " d--:" + d);
    }

    /**
     * 测试split方法
     */
    private void testStringSplit() {
//        Container<String, String> c1 = new Container<String, String>("name", "findingsea");
//        Container<String, Integer> c2 = new Container<String, Integer>("age", 24);
//        Container<Double, Double> c3 = new Container<Double, Double>(1.1, 2.2);
//        LogTool.e(c1.getKey() + " : " + c1.getValue());
//        LogTool.e(c2.getKey() + " : " + c2.getValue());
//        LogTool.e(c3.getKey() + " : " + c3.getValue());
    }

    public <T> T genericMethod(Class<T> tClass) throws java.lang.InstantiationException, IllegalAccessException {
        T instance = tClass.newInstance();
        return instance;
    }

    class Generic<T> {

        private T key;

        public T getKey() {
            return key;
        }

        public void setKey(T key) {
            this.key = key;
        }

        public <E> E showKeyName(Generic<E> container) {
            System.out.println("container key :" + container.getKey());
            //当然这个例子举的不太合适，只是为了说明泛型方法的特性。
            E test = container.getKey();
            return test;
        }
    }

    class General extends Generic {

    }

    /**
     * 测试 extends
     * 参数的类型上边界是 General
     * 就是说接受类型只能是General本身或者他的父类，子类不行
     * set，add方法不能使用
     *
     * @param list
     */
    private void testExtends(List<? extends General> list) {
        List<? extends General> generics = list;
        Generic generic = generics.get(1);
        General sup = generics.get(2);
        LogTool.e("generic:" + generic.getClass());
        LogTool.e("sup:" + sup.getClass());
    }

    /**
     * 测试 super
     * 参数的类型下边界是Generic
     * 就是说只能放入Generic本身或者它的子类，父类不行
     * get方法不能用
     */
    private void testSuper() {
        List<? super Generic> list = new ArrayList<>();
        list.add(new General());
        list.add(new Generic());
        LogTool.e("list:" + list.size());
    }

    private void test() {
        List<General> generals = new ArrayList<>();
        generals.add(new General());
        generals.add(new General());
        generals.add(new General());
        testExtends(generals);
        testSuper();

    }

    /**
     * 测试枚举类
     */
    private void testEnum(Day day) {
//        for (Day day : Day.values()) {
//            LogTool.e("day:" + day.name());
//        }

//        switch (day) {
//            case FRIDAY:
//                LogTool.e("day:" + day.name());
//                break;
//            case MONDAY:
//                LogTool.e("day:" + day.name());
//                break;
//            case SUNDAY:
//                LogTool.e("day:" + day.name());
//                break;
//            case TUESDAY:
//                LogTool.e("day:" + day.name());
//                break;
//            case SATURDAY:
//                LogTool.e("day:" + day.name());
//            case THURSDAY:
//                LogTool.e("day:" + day.name());
//                break;
//            case WEDNESDAY:
//                LogTool.e("day:" + day.name());
//                break;
//            default:
//                day.init();
//                break;
//        }


        Day.MONDAY.print();
        Day.FRIDAY.print();

    }

    /**
     * 通过反射获取对象
     */
    private void getClassData() {
        Class k = null;
        // 1. new一个对象就会产生一个class
        User user = new User();
        Class c = user.getClass();
        LogTool.e("class1:" + c);

        // 2.每一个对象都有一个.class静态方法
        Class u = User.class;
        LogTool.e("class2:" + u);

        // 通过Class.forName方法获取对象，参数必须是据对路径，即：包名+类名
        try {
            k = Class.forName("com.lengjiye.toolkit.bean.User");
            LogTool.e("class3:" + k);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // 获取所有的public构造方法
        Constructor[] list = c.getConstructors();
        for (Constructor constructor : list) {
            LogTool.e("getConstructors:" + constructor);
        }

        /**
         * 所有的构造方法(包括：私有、受保护、默认、公有)
         */
        Constructor[] listU = u.getDeclaredConstructors();
        for (Constructor constructor : listU) {
            LogTool.e("getDeclaredConstructors:" + constructor);
        }

        /**
         * 获取public的成员方法
         * 可以获取到父类的public方法
         */
        Method methods[] = u.getMethods();
        for (Method method : methods) {
            LogTool.e("method:" + method);
        }

        /**
         * 获取所有的成员方法
         * 只能获取当前类的方法
         */
        Method methodsC[] = c.getDeclaredMethods();
        for (Method method : methodsC) {
            LogTool.e("methodsC:" + method);
        }

        /**
         * 获取public成员方法
         * 根据方法名和参数获取方法
         */
        try {
            Object object= k.newInstance();
            Method method = k.getMethod("setId", int.class);
            LogTool.e("根据方法名和参数获取public方法:" + method);
            method.invoke(object, 3);
            Method method1 = k.getMethod("getId");
            int id = (int) method1.invoke(object);
            LogTool.e("根据方法名和参数获取public方法和值:" + id);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        }

        /**
         * 获取成员方法
         * 根据方法名和参数获取方法
         */
        try {
            Object object= k.newInstance();
            Method method = k.getDeclaredMethod("setTest", String.class);
            LogTool.e("根据方法名和参数获取方法:" + method);
            // 取消限制
            method.setAccessible(true);
            // 调用私有方法
            method.invoke(object,"调用私有方法");
            Method method1 = k.getDeclaredMethod("getTest");
            method1.setAccessible(true);
            String string = (String) method1.invoke(object);
            LogTool.e("根据方法名和参数获取方法和值:" + string);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        /**
         * 获取public构造方法
         * 根据传的参数 获取对应的构造方法
         * 注：如果构造方法参数是int则用int.class，如果是Integer则用Integer.class，否则报错
         */
        try {
            Constructor constructor = k.getConstructor();
            LogTool.e("constructor:" + constructor);
            // 使用构造方法调用
            // constructor.newInstance()
            // 使用此 Constructor 对象表示的构造方法来创建该构造方法的声明类的新实例，并用指定的初始化参数初始化该实例。
            User user1 = (User) constructor.newInstance();
            user1.setName("反射");
            String name = user1.getName();
            LogTool.e("name:" + name);

            // 根据参数获取对应的public构造方法
            Constructor constructorU = u.getConstructor(int.class, int.class);
            LogTool.e("constructorU:" + constructorU);

            User user2 = (User) constructorU.newInstance(20, 1);
            int age = user2.getAge();
            LogTool.e("age:" + age);
            int id = user2.getId();
            LogTool.e("id:" + id);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        /**
         * 获取构造方法（不区分public，private等）
         * 根据传的参数 获取对应的构造方法
         * 注：如果构造方法参数是int则用int.class，如果是Integer则用Integer.class，否则报错
         */
        try {
            Constructor constructor = k.getDeclaredConstructor(int.class, String.class, int.class);
            LogTool.e("constructor111:" + constructor);
            // 去掉修饰符的限制
            constructor.setAccessible(true);
            User user1 = (User) constructor.newInstance(10, "测试", 5);
            int test = user1.getAge();
            LogTool.e("test:" + test);

            // 根据参数获取对应的构造方法  这里获取的是protected方法
            Constructor constructorC = c.getDeclaredConstructor(int.class);
            // 去掉修饰符的限制
            constructorC.setAccessible(true);
            User user2 = (User) constructorC.newInstance(10);
            int age = user2.getAge();
            LogTool.e("age:" + age);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        try {
            Field field = k.getField("string");
            LogTool.e("field:" + field);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

    }

}
