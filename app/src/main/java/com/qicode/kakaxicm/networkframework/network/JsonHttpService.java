package com.qicode.kakaxicm.networkframework.network;

import com.qicode.kakaxicm.networkframework.network.interfaces.IHttpListener;
import com.qicode.kakaxicm.networkframework.network.interfaces.IHttpService;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Created by chenming on 2018/6/27
 */
public class JsonHttpService implements IHttpService {
    //请求参数
    private String url;
    private Map<String, Object> params;//请求参数
    private RequestMethod requestMethod;
    //返回
    private IHttpListener listener;

    private HttpURLConnection httpURLConnection;

    private void doGet() {
        try {
            //根据参数配置Url
            StringBuilder sb = new StringBuilder(url);
            sb.append("?");
            for (String key : params.keySet()) {
                sb.append(key).append("=").append(params.get(key)).append("&");
            }
            //去掉最后一个&
            String path = sb.toString();
            path = path.substring(0, path.length() - 1);
            URL url = new URL(path);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setDoOutput(false);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setConnectTimeout(10000);
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            httpURLConnection.setRequestMethod("GET");
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode == 200) {
                InputStream is = httpURLConnection.getInputStream();
                listener.onSuccess(is);
            } else {
                listener.onFail(responseCode, "网络请求失败");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * post请求
     */
    private void doPost() {
        OutputStreamWriter out = null;//写入请求参数
        try {
            URL url = new URL(this.url);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            //设置连接超时时间和读取超时时间
            httpURLConnection.setConnectTimeout(10000);
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            httpURLConnection.setRequestMethod("POST");
            //写入post参数
            if (params != null) {
                out = new OutputStreamWriter(httpURLConnection.getOutputStream());
                // POST的请求参数写在正文中
                for (String key : params.keySet()) {
                    String value = URLEncoder.encode(params.get(key).toString(), "UTF-8");
                    out.write(key + "=" + value + "&");
                }
                out.flush();
                out.close();
            }

            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode == 200) {
                InputStream is = httpURLConnection.getInputStream();
                listener.onSuccess(is);
            } else {
                listener.onFail(responseCode, "网络请求失败");
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 执行网络请求
     */
    @Override
    public void execute() {
        //TODO post还是get方法请求
        if(requestMethod == RequestMethod.GET){
            doGet();
        }else if(requestMethod == RequestMethod.POST){
            doPost();
        }
    }

    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    @Override
    public void setRequestMathod(RequestMethod method) {
        this.requestMethod = method;
    }

    @Override
    public void setHttpListener(IHttpListener listener) {
        this.listener = listener;
    }

}
