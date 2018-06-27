package com.qicode.kakaxicm.networkframework.network.interfaces;

import org.apache.http.HttpEntity;

/**
 * Created by chenming on 2018/6/27
 * 访问网络结果回调，执行在子线程
 */
public interface IHttpListener {
    void onSuccess(HttpEntity entity);

    void onFail(int code, String msg);
}
