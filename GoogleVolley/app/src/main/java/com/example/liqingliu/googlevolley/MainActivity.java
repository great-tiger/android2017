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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends Activity {

    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    Button btn5;
    TextView detail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        detail = findViewById(R.id.detail);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendSimpleRequest(v);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendSetupRequest(v);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonObjectRequest(v);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonArrayRequest(v);
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customRequest(v);
            }
        });
    }

    private void customRequest(View v) {

    }

    private void jsonArrayRequest(View v) {
        String url = "http://10.0.2.2:5000/get_array_json";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    detail.setText(response.getJSONObject(0).getString("username"));
                } catch (JSONException e) {
                    e.printStackTrace();
                };
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                detail.setText(error.getMessage());
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    private void jsonObjectRequest(View v) {
        String url = "http://10.0.2.2:5000/get_json";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(new JsonObjectRequest(Request.Method.GET, url,null,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    detail.setText(response.getString("username").toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                detail.setText(error.getMessage());
            }
        }));
    }

    private void sendSetupRequest(View v) {
        String url = "http://10.0.2.2:5000";
        //MySingleton 中的图片部分以后学习
        RequestQueue requestQueue = MySingleton.getInstance(this).getRequestQueue();
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
        requestQueue.add(stringRequest);
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
        // if(requestQueue!=null){
        //    requestQueue.cancelAll("TEST");
        // }

    }

    @Override
    protected void onStop() {
        super.onStop();

    }
}
