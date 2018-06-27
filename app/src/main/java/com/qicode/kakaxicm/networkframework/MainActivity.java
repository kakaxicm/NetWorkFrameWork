package com.qicode.kakaxicm.networkframework;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.qicode.kakaxicm.networkframework.network.Volley;
import com.qicode.kakaxicm.networkframework.network.interfaces.IDataListener;

public class MainActivity extends AppCompatActivity {
    String url = "http://wenwan.cacang.com/wenwan/index/xxxxx";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void login(View view) {
        User user = new User();
        user.setName("13343491234");
        user.setPassword("123456");
        for (int i = 0; i < 50; i++) {
            Volley.sendRequest(user, url, UserResponse.class, new IDataListener<UserResponse>() {
                @Override
                public void onSuccess(UserResponse loginResponse) {
//                    Toast.makeText(MainActivity.this, loginResponse.toString(), Toast.LENGTH_LONG).show();
                    Log.e("NetWork-Response", loginResponse.toString());
                }

                @Override
                public void onFail(String message) {
                    Log.e("NetWork", message);
                }
            });
        }


    }
}
