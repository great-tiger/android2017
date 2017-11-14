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

                            //java.io.FileNotFoundException getInputStream 这个异常是因为服务器不支持POST造成的
                            connection.setDoOutput(true);
                            connection.setRequestMethod("POST");
                            OutputStream outputStream = connection.getOutputStream();
                            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"utf-8"));
                            bufferedWriter.write("username=clh&password=test");
                            bufferedWriter.flush();
                            bufferedWriter.close();

                            InputStream inputStream = connection.getInputStream();
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
                }.execute("http://10.0.2.2:3000");
            }
        });
    }
}
