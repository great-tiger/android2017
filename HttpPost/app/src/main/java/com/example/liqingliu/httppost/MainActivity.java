package com.example.liqingliu.httppost;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends Activity {
    Button btnPost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnPost = findViewById(R.id.btnPost);
        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AsyncTask<String,Float,String>(){

                    @Override
                    protected String doInBackground(String... params) {
                        try {
                            URL url = new URL(params[0]);
                            HttpURLConnection connection =(HttpURLConnection)url.openConnection();
                            InputStream inputStream = connection.getInputStream();
                            OutputStream outputStream = connection.getOutputStream();
                            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
                            connection.setRequestMethod("POST");
                            bufferedWriter.write("username=clh&password=test");
                            bufferedWriter.flush();
                            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                            String line = null;
                            StringBuilder builder = new StringBuilder();
                            while ((line = bufferedReader.readLine())!=null){
                                builder.append(line);
                            }
                            bufferedReader.close();
                            inputStream.close();
                            return builder.toString();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        return null;
                    }

                    @Override
                    protected void onPostExecute(String s) {
                        System.out.println(s);
                        super.onPostExecute(s);
                    }
                }.execute("http://fanyi.youdao.com/openapi.do?keyfrom=%3Ckeyfrom%3E&key=%3Ckey%3E&type=data&doctype=%3Cdoctype%3E&version=1.1&q=%E8%A6%81%E7%BF%BB%E8%AF%91%E7%9A%84%E6%96%87%E6%9C%AC");
            }
        });
    }
}
