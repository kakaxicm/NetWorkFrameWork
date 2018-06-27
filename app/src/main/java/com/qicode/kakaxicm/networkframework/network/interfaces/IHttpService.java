package com.qicode.kakaxicm.networkframework.network.interfaces;

import com.qicode.kakaxicm.networkframework.network.RequestMethod;

import java.util.Map;

/**
 * Created by chenming on 2018/6/27
 * 顶层的业务描述
 */
public interface IHttpService {

    void setUrl(String url);

    /**
     * 设置请求参数
     * @param params
     */
    void setParams(Map<String, Object> params);

    /**
     * 设置请求方式
     * @param method
     */
    void setRequestMathod(RequestMethod method);

    /**
     * 设置结果监听
     * @param listener
     */
    void setHttpListener(IHttpListener listener);

    /**
     * 执行网络请求
     */
    void execute();

}
