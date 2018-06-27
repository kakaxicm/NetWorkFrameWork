package com.qicode.kakaxicm.networkframework.network;

import com.alibaba.fastjson.JSON;
import com.qicode.kakaxicm.networkframework.network.interfaces.IHttpService;

import java.util.Map;

/**
 * Created by chenming on 2018/6/27
 */
public class HttpTask implements Runnable{
    private IHttpService httpService;
    public HttpTask(RequestHolder holder){
        //配置httpService
        httpService = holder.getHttpService();
        httpService.setHttpListener(holder.getHttpListener());
        httpService.setUrl(holder.getUrl());
        httpService.setRequestMathod(holder.getMethod());
        Map<String, Object> params = holder.getRequestParams();
        httpService.setParams(params);
    }

    @Override
    public void run() {
        httpService.execute();
    }
}
