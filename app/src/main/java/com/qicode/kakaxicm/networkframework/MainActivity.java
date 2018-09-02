package com.qicode.kakaxicm.networkframework;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.qicode.kakaxicm.networkframework.network.RequestMethod;
import com.qicode.kakaxicm.networkframework.network.Volley;
import com.qicode.kakaxicm.networkframework.network.interfaces.IDataListener;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    String url = "http://wenwan.cacang.com/wenwan/index/xxxxx";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void login(View view) {

        Map<String, Object> params = new HashMap<>();
        params.put("name", "13343491234");
        params.put("password", "123456");
        for (int i = 0; i < 50; i++) {
            Volley.sendRequestForJson(url, RequestMethod.POST,params, UserResponse.class, new IDataListener<UserResponse>() {
                @Override
                public void onSuccess(UserResponse loginResponse) {
                    Log.e("NetWorkResponse", loginResponse.toString());
                }

                @Override
                public void onFail(String message) {
                    Log.e("NetWork", message);
                }
            });
        }

    }
}
