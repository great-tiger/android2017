package com.example.liqingliu.baiduoauth;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.baidu.api.AsyncBaiduRunner;
import com.baidu.api.Baidu;
import com.baidu.api.BaiduDialog;
import com.baidu.api.BaiduDialogError;
import com.baidu.api.BaiduException;
import com.google.gson.Gson;

import java.io.IOException;


public class MainActivity extends Activity {

    Baidu baidu;
    TextView tvResult;
    String appKey="lIvw2fIs521r8toy0xa7LElR";
    Button btnGetinfo;
    Button btnGetinfoAsync;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        baidu = new Baidu(appKey,this);
        tvResult = findViewById(R.id.tvResult);
        btnGetinfo = findViewById(R.id.btnGetinfo);
        btnGetinfoAsync = findViewById(R.id.btnGetinfoAsync);
        btnGetinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getInfo(v);
            }
        });
        btnGetinfoAsync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getInfoAsync(v);
            }
        });
    }

    private void getInfoAsync(View v) {
        AsyncBaiduRunner runner = new AsyncBaiduRunner(baidu);
        runner.request("https://openapi.baidu.com/rest/2.0/passport/users/getInfo", null, "GET", new AsyncBaiduRunner.RequestListener() {
            @Override
            public void onComplete(final String s) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson = new Gson();
                        UserInfo info = gson.fromJson(s,UserInfo.class);
                        tvResult.setText(info.getUsername());
                    }
                });
            }

            @Override
            public void onIOException(IOException e) {

            }

            @Override
            public void onBaiduException(BaiduException e) {

            }
        });
    }

    public void startOauth(View view){
        baidu.authorize(this, true, true, new BaiduDialog.BaiduDialogListener() {
            @Override
            public void onComplete(final Bundle bundle) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvResult.setText(baidu.getAccessToken());
                    }
                });
            }

            @Override
            public void onBaiduException(BaiduException e) {

            }

            @Override
            public void onError(BaiduDialogError baiduDialogError) {

            }

            @Override
            public void onCancel() {

            }
        });
    }

    public void getInfo(View view){
        try {
            String result = baidu.request("https://openapi.baidu.com/rest/2.0/passport/users/getInfo",null,"GET");
            tvResult.setText(result);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BaiduException e) {
            e.printStackTrace();
        }
    }

    public class UserInfo{
        public UserInfo(){

        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        String username;
    }
}
