package com.qicode.kakaxicm.networkframework.network;

import com.qicode.kakaxicm.networkframework.network.interfaces.IHttpListener;
import com.qicode.kakaxicm.networkframework.network.interfaces.IHttpService;

/**
 * Created by chenming on 2018/6/27
 * 请求信息和回调封装
 * T为请求
 */
public class RequestHolder<T> {
    /**
     * 执行下载
     */
    private IHttpService httpService;
    /**
     * 获取数据的回调
     */
    private IHttpListener httpListener;

    /**
     * 请求参数对应的实体
     */
    private T requestInfo;

    /**
     * 请求url
     */
    String url;

    public IHttpService getHttpService() {
        return httpService;
    }

    public void setHttpService(IHttpService httpService) {
        this.httpService = httpService;
    }

    public IHttpListener getHttpListener() {
        return httpListener;
    }

    public void setHttpListener(IHttpListener httpListener) {
        this.httpListener = httpListener;
    }

    public T getRequestInfo() {
        return requestInfo;
    }

    public void setRequestInfo(T requestInfo) {
        this.requestInfo = requestInfo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
