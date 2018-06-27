package com.qicode.kakaxicm.networkframework.network;

import com.qicode.kakaxicm.networkframework.network.interfaces.IHttpListener;
import com.qicode.kakaxicm.networkframework.network.interfaces.IHttpService;

import java.util.Map;

/**
 * Created by chenming on 2018/6/27
 * 请求信息和回调封装
 * T为请求
 */
public class RequestHolder {
    /**
     * 执行下载
     */
    private IHttpService httpService;
    /**
     * 获取数据的回调
     */
    private IHttpListener httpListener;

    /**
     * 请求url
     */
    String url;

    /**
     * 请求参数参数
     */
    private Map<String, Object> params;

    private RequestMethod method;

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

    public Map<String, Object> getRequestParams() {
        return params;
    }

    public void setRequestParams(Map<String, Object> params) {
        this.params = params;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setMethod(RequestMethod method) {
        this.method = method;
    }

    public RequestMethod getMethod() {
        return method;
    }

}
