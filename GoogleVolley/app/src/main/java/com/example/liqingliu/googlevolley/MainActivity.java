package com.example.liqingliu.googlevolley;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends Activity {

    Button btn1;
    TextView detail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = findViewById(R.id.btn1);
        detail = findViewById(R.id.detail);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendSimpleRequest(v);
            }
        });
    }

    private void sendSimpleRequest(View v) {
        String url = "http://10.0.2.2:5000";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                detail.setText(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                detail.setText(error.getMessage());
            }
        });

        stringRequest.setTag("TEST");
        requestQueue.add(stringRequest);
// 可以用下面的代码取消请求，不会调用回调函数了
//        if(requestQueue!=null){
//            requestQueue.cancelAll("TEST");
//        }

    }

    @Override
    protected void onStop() {
        super.onStop();

    }
}
