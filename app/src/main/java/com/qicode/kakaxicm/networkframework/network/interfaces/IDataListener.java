package com.qicode.kakaxicm.networkframework.network.interfaces;

/**
 * Created by chenming on 2018/6/27
 * M为返回给调用者的对象类型
 */
public interface IDataListener<M> {
    /**
     * 回调给调用者
     * @param response
     */
    void onSuccess(M response);

    void onFail(String message);
}
