package com.example.liqingliu.newsapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liqingliu on 17/11/10.
 */

public class HttpUtils {
    public static void getData(final String path ,final Handler handler){
        new Thread(){
            @Override
            public void run() {
                StringBuilder builder = new StringBuilder();
                List<NewsItem> items = new ArrayList<>();
                try {
                    URL url = new URL(path);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.connect();
                    urlConnection.setRequestMethod("GET");
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    String line = "";
                    while ( (line = bufferedReader.readLine()) != null){
                        builder.append(line);
                    }
                    JSONArray jsonArray = new JSONArray(builder.toString());
                    for(int i =0;i<jsonArray.length();i++){
                        int id = jsonArray.getJSONObject(i).getInt("id");
                        String title = jsonArray.getJSONObject(i).getString("title");
                        String desc = jsonArray.getJSONObject(i).getString("des");
                        String picUrl = jsonArray.getJSONObject(i).getString("pic_url");
                        String contentUrl = jsonArray.getJSONObject(i).getString("content_url");
                        String time = jsonArray.getJSONObject(i).getString("time");
                        NewsItem obj = new NewsItem(id, title, desc,  picUrl, time, contentUrl);
                        items.add(obj);
                    }

                    Message message = Message.obtain();
                    message.obj = items;
                    handler.sendMessage(message);
                    bufferedReader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
    public static void setImage(final ImageView view, final String path){
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    URL url = new URL(path);
                    URLConnection urlConnection = url.openConnection();
                    InputStream inputStream = urlConnection.getInputStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    view.setImageBitmap(bitmap);
                    inputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
