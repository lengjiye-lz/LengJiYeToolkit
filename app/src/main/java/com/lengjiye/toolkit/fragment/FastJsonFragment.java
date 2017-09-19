package com.lengjiye.toolkit.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.lengjiye.toolkit.R;
import com.lengjiye.toolkit.bean.User;
import com.lengjiye.toolkit.view.MaskTextView;

import org.xutils.common.util.LogUtil;

/**
 * Fastjson 解析数据
 * Created by lz on 2016/7/19.
 */
public class FastJsonFragment extends BaseFragment {

    private TextView text;
    private Button button1, button2, button3, button4, button5;

    public FastJsonFragment() {
    }

    /**
     * 创建一个fragment
     *
     * @return
     */
    public static FastJsonFragment newInstance() {
        FastJsonFragment fragment = new FastJsonFragment();
        return fragment;
    }

    @Override
    public View createView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fastjson, container, false);
    }

    @Override
    public void initView(View view) {
        text = (TextView) view.findViewById(R.id.text);
        button1 = (Button) view.findViewById(R.id.button1);
        button2 = (Button) view.findViewById(R.id.button2);
        button3 = (Button) view.findViewById(R.id.button3);
        button4 = (Button) view.findViewById(R.id.button4);
        button5 = (Button) view.findViewById(R.id.button5);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);

    }


    @Override
    public void initData() {
        super.initData();
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.button1:
                toJsonString();
                break;
            case R.id.button2:
                parseJsonObject();
                break;
            case R.id.button3:
                parseBeanObject();
                break;
            case R.id.button4:
                bean2Json();
                break;
            case R.id.button5:
                parseJSONAndBeanEachother();
                break;
        }
    }

    /**
     * 设置text的值
     *
     * @param string
     */
    private void setText(String string) {
        text.setText(string);
    }


    /**
     * 序列化
     */
    public void toJsonString() {
        User user = new User(1, "maks", 105);
        String text = JSON.toJSONString(user);
        setText("toJsonString()方法：text=" + text);
    }

    /**
     * 反序列化为json对象
     */
    public void parseJsonObject() {
        String text = "{\"age\":105,\"id\":\"testFastJson001\",\"name\":\"maks\"}";
        JSONObject json = JSON.parseObject(text);
        setText("parseJsonObject()方法：json==" + json);
        // 输出结果：json=={"age":105,"id":"testFastJson001","name":"maks"}
    }

    /**
     * 反序列化为javaBean对象
     */
    public void parseBeanObject() {
        String text = "{\"age\":105,\"id\":\"testFastJson001\",\"name\":\"maks\"}";
        User user = (User) JSON.parseObject(text, User.class);
        setText("parseBeanObject()方法：user==" + user.getId() + "," + user.getName() + "," + user.getAge());
        // 输出结果：user==testFastJson001,maks,105
    }

    /**
     * 将javaBean转化为json对象
     */
    public void bean2Json() {
        User user = new User(1, "maks", 105);
        JSONObject jsonObj = (JSONObject) JSON.toJSON(user);
        setText("bean2Json()方法：jsonObj==" + jsonObj);
        // 输出结果：jsonObj=={"age":105,"id":"testFastJson001","name":"maks"}
    }

    /**
     * 全序列化 直接把java bean序列化为json文本之后，能够按照原来的类型反序列化回来。支持全序列化，需要打开SerializerFeature.WriteClassName特性
     */
    public void parseJSONAndBeanEachother() {

        String s = "";
        User user = new User(1, "maks", 105);
        SerializerFeature[] featureArr = {SerializerFeature.WriteClassName};
        String text = JSON.toJSONString(user, featureArr);
        s = "parseJSONAndBeanEachother()方法：text==" + text;
        // 输出结果：text=={"@type":"fastJson.test.User","age":105,"id":"testFastJson001","name":"maks"}
        ParserConfig.getGlobalInstance().addAccept("com.lengjiye.toolkit.");
        User userObj = (User) JSON.parse(text);
        s += "\n" + "parseJSONAndBeanEachother()方法：userObj==" + userObj.getId() + "," + userObj.getName() + "," +
                "" + userObj.getAge();
        setText(s);
        // 输出结果：userObj==testFastJson001,maks,105
    }
}
