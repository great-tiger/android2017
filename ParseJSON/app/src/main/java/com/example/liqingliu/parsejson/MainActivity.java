package com.example.liqingliu.parsejson;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends Activity implements View.OnClickListener {

    Button btnCreateJson;
    Button btnReadJson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnCreateJson = findViewById(R.id.btnCreateJson);
        btnReadJson = findViewById(R.id.btnReadJson);
        btnCreateJson.setOnClickListener(this);
        btnReadJson.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnCreateJson:
                createJson();
                break;
            case R.id.btnReadJson:
                readJson();
                break;
        }
    }

    private void readJson() {
        try {
            StringBuilder builder = new StringBuilder();
            String line = null;
            BufferedReader bufferReader = new BufferedReader(new InputStreamReader(getAssets().open("test.json")));
            while ((line = bufferReader.readLine())!=null){
                builder.append(line);
            }

            String jsonString = builder.toString();

            JSONObject root= new JSONObject(jsonString);
            String cat =  root.getString("cat");

            System.out.println("cat:"+cat);

            //可以通过这种方式判断是否有相应的key
            //如果没有的话，直接读取是会报异常的
            System.out.println(root.has("languages"));

            JSONArray arr = root.getJSONArray("languages");
            for(int i=0;i<arr.length();i++){
                System.out.println("<------>");
                JSONObject o = arr.getJSONObject(i);
                int id = o.getInt("id");
                String language = o.getString("lan");
                String tool = o.getString("tool");
                System.out.println("id:"+id+" language:"+language+" tool:"+tool);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createJson() {
        try {
            JSONObject root = new JSONObject();
            root.put("cat","it");

            JSONArray lan1 = new JSONArray();

            JSONObject obj1 = new JSONObject();
            obj1.put("id",1);
            obj1.put("lan","C#");
            obj1.put("tool","VS");
            lan1.put(obj1);

            JSONObject obj2 = new JSONObject();
            obj2.put("id",2);
            obj2.put("lan","Swift");
            obj2.put("tool","XCode");
            lan1.put(obj2);

            JSONObject obj3 = new JSONObject();
            obj3.put("id",1);
            obj3.put("lan","java");
            obj3.put("tool","Eclipse");
            lan1.put(obj3);

            root.put("languages",lan1);

            String json = root.toString();

            System.out.println(json);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
