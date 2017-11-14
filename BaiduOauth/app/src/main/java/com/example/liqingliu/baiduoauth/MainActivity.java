package com.example.liqingliu.baiduoauth;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.baidu.api.Baidu;
import com.baidu.api.BaiduDialog;
import com.baidu.api.BaiduDialogError;
import com.baidu.api.BaiduException;

public class MainActivity extends Activity {

    Baidu baidu;
    TextView tvResult;
    String appKey="lIvw2fIs521r8toy0xa7LElR";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        baidu = new Baidu(appKey,this);
        tvResult = findViewById(R.id.tvResult);
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
}
