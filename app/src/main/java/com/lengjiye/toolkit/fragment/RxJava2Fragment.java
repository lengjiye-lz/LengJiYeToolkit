package com.lengjiye.toolkit.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lengjiye.toolkit.R;
import com.lengjiye.tools.LogTool;
import com.lengjiye.tools.ToastTool;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Fastjson 解析数据
 * Created by lz on 2016/7/19.
 */
public class RxJava2Fragment extends BaseFragment {


    @BindView(R.id.text)
    TextView textView;

    private Unbinder unbinder;

    public RxJava2Fragment() {
    }

    /**
     * 创建一个fragment
     *
     * @return
     */
    public static RxJava2Fragment newInstance() {
        RxJava2Fragment fragment = new RxJava2Fragment();
        return fragment;
    }

    @Override
    public View createView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rxjava2, container, false);
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

    @OnClick({R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_4, R.id.btn_5, R.id.btn_6})
    public void onClicks(View v) {
        switch (v.getId()) {
            case R.id.btn_1:
                test1();
                break;
            case R.id.btn_2:
                test2();
                break;
            case R.id.btn_3:
                test3();
                break;
            case R.id.btn_4:
                test4();
                break;
            case R.id.btn_5:
                test5();
                break;
            case R.id.btn_6:
                test6();
                break;
            default:
                break;
        }
    }

    /**
     * rxjava2 分开步骤
     */
    private void test1() {
        Observable<Integer> objectObservable = Observable.create(new ObservableOnSubscribe<Integer>() {

            // ObservableEmitter： Emitter是发射器的意思，那就很好猜了，这个就是用来发出事件的，
            // 它可以发出三种类型的事件，通过调用emitter的onNext(T value)、onComplete()和
            // onError(Throwable error)就可以分别发出next事件、complete事件和error事件。
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                LogTool.e("subscribe：" + 1);
                e.onNext(2);
                LogTool.e("subscribe：" + 2);
                e.onNext(3);
                LogTool.e("subscribe：" + 3);
                e.onComplete();
                LogTool.e("subscribe：" + 4);
                e.onNext(4);
            }
        });

        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                LogTool.e("onSubscribe");
            }

            @Override
            public void onNext(Integer integer) {
                LogTool.e("onNext:" + integer);
            }

            @Override
            public void onError(Throwable e) {
                LogTool.e("onError");
            }

            @Override
            public void onComplete() {
                LogTool.e("onComplete");
            }
        };

        objectObservable.subscribe(observer);
    }

    /**
     * rxjava2 链式写法
     */
    private void test2() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                LogTool.e("subscribe：" + 1);
                e.onNext(2);
                LogTool.e("subscribe：" + 2);
                e.onNext(3);
                LogTool.e("subscribe：" + 3);
                e.onComplete();
                LogTool.e("subscribe：" + 4);
                e.onNext(4);
            }
        }).subscribe(new Observer<Integer>() {
            private Disposable mDisposable;

            @Override
            public void onSubscribe(Disposable d) {
                LogTool.e("onSubscribe");
                mDisposable = d;
            }

            @Override
            public void onNext(Integer integer) {
                LogTool.e("onNext:" + integer);
                if (integer == 2) {
                    LogTool.e("mDisposfollowable.dispose():" + mDisposable.isDisposed());
                    mDisposable.dispose();
                    LogTool.e("mDisposable.dispose():" + mDisposable.isDisposed());
                }
            }

            @Override
            public void onError(Throwable e) {
                LogTool.e("onError" + e);
            }

            @Override
            public void onComplete() {
                LogTool.e("onComplete");
            }
        });
    }

    /**
     * 只接受onNext事件和error事件
     */
    private void test3() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                LogTool.e("subscribe：" + 1);
                e.onNext(2);
                LogTool.e("subscribe：" + 2);
                e.onNext(3);
                LogTool.e("subscribe：" + 3);
                e.onError(new Throwable("test"));
                LogTool.e("subscribe：" + 4);
                e.onNext(4);
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                LogTool.e("onNext:" + integer);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                LogTool.e("onError:" + throwable);
            }
        });
    }

    /**
     * 简单线程切换
     */
    private void test4() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                LogTool.e("subscribe：" + 1);
                LogTool.e("Observable thread is : " + Thread.currentThread().getName());
            }
        }).subscribeOn(Schedulers.newThread())
                // 多次调用subscribeOn不起作用
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                // 多次调用observeOn会切换多次
                .observeOn(Schedulers.io())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        LogTool.e("onNext:" + integer);
                        LogTool.e("Observe thread is : " + Thread.currentThread().getName());
                    }
                });
    }

    /**
     * 模拟网络请求
     */
    private void test5() {

        ObservableSource<Integer> observableSource1 = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                Thread.sleep(2 * 1000);
                e.onNext(1);
                LogTool.e("observableSource1：" + 1);
            }
        }).subscribeOn(Schedulers.io());

        ObservableSource<Integer> observableSource2 = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                Thread.sleep(1 * 1000);
                e.onNext(2);
                LogTool.e("observableSource2：" + 2);
            }
        }).subscribeOn(Schedulers.io());

        Observable.zip(observableSource1, observableSource2, new BiFunction<Integer, Integer, Integer>() {

            @Override
            public Integer apply(Integer integer, Integer integer2) throws Exception {
                LogTool.e("integer:" + integer + " integer2:" + integer2);
                return integer + integer2;
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        LogTool.e("onNext:" + integer);
                    }
                });
    }

    /**
     * 测试map操作符
     */
    private void test6() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("1a");
            }
        }).map(new Function<String, Integer>() {
            @Override
            public Integer apply(String integer) throws Exception {
                return Integer.valueOf(integer);
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer s) throws Exception {
                textView.setText("转换测试：" + s);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                ToastTool.getInstance().show(getActivity().getApplicationContext(), "转换错误：" + throwable.getMessage());
            }
        });
    }

    /**
     * 测试zip操作符
     */
    private void test7() {
        Observable observableString = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                if (e.isDisposed()) {
                    return;
                }
                e.onNext("a");
                LogTool.e("a");
                e.onNext("b");
                LogTool.e("b");
                e.onNext("c");
                LogTool.e("c");
                e.onNext("d");
                LogTool.e("d");
            }
        });

        Observable observableInt = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                if (e.isDisposed()) {
                    return;
                }
                e.onNext(1);
                LogTool.e("1");
                e.onNext(2);
                LogTool.e("2");
                e.onNext(3);
                LogTool.e("3");
                e.onNext(4);
                LogTool.e("4");
                e.onNext(5);
                LogTool.e("5");
            }
        });

        Observable.zip(observableInt, observableString, new BiFunction<Integer, String, String>() {

            @Override
            public String apply(Integer integer, String s) throws Exception {
                return s + integer;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String o) throws Exception {
                LogTool.e("o:" + o);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null) {
            unbinder.unbind();
            unbinder = null;
        }
    }
}
