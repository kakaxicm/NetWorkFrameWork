package com.qicode.kakaxicm.networkframework.network;

import com.alibaba.fastjson.JSON;
import com.qicode.kakaxicm.networkframework.network.interfaces.IHttpService;

import java.io.UnsupportedEncodingException;

/**
 * Created by chenming on 2018/6/27
 */
public class HttpTask<T> implements Runnable{
    private IHttpService httpService;
    public HttpTask(RequestHolder<T> holder){
        //配置httpService
        httpService = holder.getHttpService();
        httpService.setHttpListener(holder.getHttpListener());
        httpService.setUrl(holder.getUrl());
        T request = holder.getRequestInfo();
        String requestInfo = JSON.toJSONString(request);
        try {
            httpService.setRequestData(requestInfo.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        httpService.execute();
    }
}
