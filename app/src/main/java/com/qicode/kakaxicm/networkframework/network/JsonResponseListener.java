package com.qicode.kakaxicm.networkframework.network;

import android.os.Handler;
import android.os.Looper;

import com.alibaba.fastjson.JSON;
import com.qicode.kakaxicm.networkframework.network.interfaces.IDataListener;
import com.qicode.kakaxicm.networkframework.network.interfaces.IHttpListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


/**
 * Created by chenming on 2018/6/27
 * json请求的回调，M为返回的对象类型
 * 拿到HttpEntity，获取json串，转化成M对象
 */
public class JsonResponseListener<M> implements IHttpListener {
    private IDataListener<M> dataListener;
    private Class<M> responseClazz;//用于json转换成M对象

    private Handler mainHandler = new Handler(Looper.getMainLooper());//主线程调用dataListener

    public JsonResponseListener(IDataListener<M> dataListener, Class<M> responseClazz) {
        this.dataListener = dataListener;
        this.responseClazz = responseClazz;
    }

    @Override
    public void onSuccess(InputStream inputStream) {
        //获取输入流
        String jsonStr = getContent(inputStream);
        final M m = JSON.parseObject(jsonStr, responseClazz);
        if (m != null) {
            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    if (dataListener != null) {
                        dataListener.onSuccess(m);
                    }
                }
            });
        } else {
            onFail(-1, "JSON解析异常");
        }
    }

    /**
     * 输入流转换成string
     *
     * @param inputStream
     * @return
     */
    private String getContent(InputStream inputStream) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (Exception e) {
            onFail(-1, e.getLocalizedMessage());
        } finally {

            try {
                inputStream.close();
            } catch (IOException e) {
            }

        }
        return sb.toString();
    }

    @Override
    public void onFail(int code, final String msg) {
        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                if (dataListener != null) {
                    dataListener.onFail(msg);
                }
            }
        });
    }

}
