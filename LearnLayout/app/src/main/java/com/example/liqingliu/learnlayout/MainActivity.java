package com.example.liqingliu.learnlayout;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

    private WebView webView;
    private Button button;
    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.go);
        editText = findViewById(R.id.url);
        webView = findViewById(R.id.browser);
        webView.setWebViewClient(new WebViewClient(){
        });
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                webView.loadUrl(editText.getText().toString());
            }
        });

    }
}
