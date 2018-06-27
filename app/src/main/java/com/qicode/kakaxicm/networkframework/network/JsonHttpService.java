package com.qicode.kakaxicm.networkframework.network;

import com.qicode.kakaxicm.networkframework.network.interfaces.IHttpListener;
import com.qicode.kakaxicm.networkframework.network.interfaces.IHttpService;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;

/**
 * Created by chenming on 2018/6/27
 */
public class JsonHttpService implements IHttpService {
    //请求参数
    private String url;
    private byte[] requestData;
    //返回
    private IHttpListener listener;

    private HttpClient httpClient = new DefaultHttpClient();
    private HttpPost httpPost;
    private HttpResponseHandler httpResponseHandler = new HttpResponseHandler();
    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public void setRequestData(byte[] requestData) {
        this.requestData = requestData;
    }

    @Override
    public void setHttpListener(IHttpListener listener) {
        this.listener = listener;
    }

    /**
     * 执行网络请求
     */
    @Override
    public void execute() {
        httpPost = new HttpPost(url);
        ByteArrayEntity byteArrayEntity = new ByteArrayEntity(requestData);
        httpPost.setEntity(byteArrayEntity);
        try {
            httpClient.execute(httpPost, httpResponseHandler);
        } catch (IOException e) {
            listener.onFail(-1, e.getLocalizedMessage());
        }
    }

    private class HttpResponseHandler extends BasicResponseHandler{
        @Override
        public String handleResponse(HttpResponse response) throws IOException {
            int statusCode = response.getStatusLine().getStatusCode();
            if(statusCode == 200){
                listener.onSuccess(response.getEntity());
            }else{
                listener.onFail(statusCode, "网络请求失败");
            }
            return null;
        }
    }
}
