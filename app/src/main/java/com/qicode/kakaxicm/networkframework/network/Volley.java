package com.qicode.kakaxicm.networkframework.network;

import com.qicode.kakaxicm.networkframework.network.interfaces.IDataListener;
import com.qicode.kakaxicm.networkframework.network.interfaces.IHttpListener;
import com.qicode.kakaxicm.networkframework.network.interfaces.IHttpService;

import java.util.concurrent.FutureTask;

/**
 * Created by chenming on 2018/6/27
 */
public class Volley {
    public static <T, M> void sendRequest(T requestBean, String url, Class<M> responseClazz, IDataListener<M> listener){
        RequestHolder<T> holder = new RequestHolder<>();
        holder.setUrl(url);
        holder.setRequestInfo(requestBean);

        IHttpService jsonHttpService = new JsonHttpService();
        IHttpListener jsonResponseListener = new JsonResponseListener<M>(listener, responseClazz);

        holder.setHttpService(jsonHttpService);
        holder.setHttpListener(jsonResponseListener);
        HttpTask<T> task = new HttpTask<>(holder);
        ThreadPoolManager manager = ThreadPoolManager.getsInstance();
        FutureTask futureTask = new FutureTask(task, null);
        manager.execute(futureTask);
    }
}
