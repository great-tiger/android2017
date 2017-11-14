package com.example.liqingliu.learnasynctask;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends Activity {
    private Button btn;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.button);
        textView = findViewById(R.id.textView);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AsyncTask<String,Float,String>(){

                    @Override
                    protected String doInBackground(String... params) {
                        String line;
                        StringBuilder builder = new StringBuilder();
                        try {
                            URL url = new URL(params[0]);
                            URLConnection connection = url.openConnection();
                            connection.connect();
                            int total = connection.getContentLength();
                            InputStream inputStream = connection.getInputStream();
                            //字节到字符的转化
                            InputStreamReader iReader = new InputStreamReader(inputStream);
                            BufferedReader reader = new BufferedReader(iReader);
                            System.out.println("-->"+reader.readLine());
                            while ((line = reader.readLine())!=null){
                                builder.append(line);
                                int receivedTotal = builder.toString().getBytes().length;
                                System.out.println("-------------------------");
                                System.out.println(receivedTotal);
                                System.out.println(total);
                                System.out.println((float)(receivedTotal/total));
                                publishProgress((float)receivedTotal/total);
                            }
                            reader.close();
//                            iReader.close();
                            inputStream.close();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        System.out.println("-->"+builder.toString());
                        return builder.toString();
                    }

                    @Override
                    protected void onPostExecute(String s) {
                        super.onPostExecute(s);
                        textView.setText(s);

                    }

                    @Override
                    protected void onProgressUpdate(Float... values) {
                        super.onProgressUpdate(values);
                        System.out.println("--progress" + values[0]);
                    }
                }.execute("http://www.qikan.com/");
            }
        });
    }
}
