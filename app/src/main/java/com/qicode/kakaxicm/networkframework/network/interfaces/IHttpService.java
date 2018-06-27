package com.qicode.kakaxicm.networkframework.network.interfaces;

/**
 * Created by chenming on 2018/6/27
 * 顶层的业务描述
 */
public interface IHttpService {

    void setUrl(String url);

    /**
     * 设置请求参数
     * @param requestData
     */
    void setRequestData(byte[] requestData);

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
