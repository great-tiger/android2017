package com.example.liqingliu.learnsharedpreferences;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends Activity {
    private static final String MyPreference = "MyPreference";
    private Button ReadPreferences;
    private Button WritePreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ReadPreferences = findViewById(R.id.ReadPreferences);
        WritePreferences = findViewById(R.id.WritePreferences);

        ReadPreferences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp = getSharedPreferences(MyPreference,0);
                String name = sp.getString("name","clh");
                Toast.makeText(MainActivity.this,name,Toast.LENGTH_SHORT).show();

            }
        });

        WritePreferences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp = getSharedPreferences(MyPreference,0);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("name","clh1");
                editor.commit();
                Toast.makeText(MainActivity.this,"保存成功!",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
