package com.example.liqingliu.newsapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Main2Activity extends Activity {
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        webView = findViewById(R.id.webView);
        Intent i = getIntent();
        String url = i.getStringExtra("contentUrl");
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);
    }
}
