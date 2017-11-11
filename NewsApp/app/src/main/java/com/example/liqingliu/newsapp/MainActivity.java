package com.example.liqingliu.newsapp;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    ListView lv;
    NewsAdapter adapter;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = findViewById(R.id.lv);


        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                adapter = new NewsAdapter(MainActivity.this,(List<NewsItem>)msg.obj);
                lv.setAdapter(adapter);
            }
        };

        HttpUtils.getData("http://192.168.2.107/php/news.php",handler);
    }
}
