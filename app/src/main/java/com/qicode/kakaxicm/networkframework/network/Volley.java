package com.qicode.kakaxicm.networkframework.network;

import com.qicode.kakaxicm.networkframework.network.interfaces.IDataListener;
import com.qicode.kakaxicm.networkframework.network.interfaces.IHttpListener;
import com.qicode.kakaxicm.networkframework.network.interfaces.IHttpService;

import java.util.Map;
import java.util.concurrent.FutureTask;

/**
 * Created by chenming on 2018/6/27
 */
public class Volley {
    public static <M> void sendRequest(String url, RequestMethod method, Map<String, Object> params, Class<M> responseClazz, IDataListener<M> listener) {
        RequestHolder holder = new RequestHolder();
        holder.setUrl(url);//配置url
        holder.setMethod(method);
        holder.setRequestParams(params);//配置请求参数

        IHttpService jsonHttpService = new JsonHttpService();
        IHttpListener jsonResponseListener = new JsonResponseListener<M>(listener, responseClazz);

        holder.setHttpService(jsonHttpService);
        holder.setHttpListener(jsonResponseListener);
        HttpTask task = new HttpTask(holder);
        ThreadPoolManager manager = ThreadPoolManager.getsInstance();
        FutureTask futureTask = new FutureTask(task, null);
        manager.execute(futureTask);
    }
}
