package com.example.liqingliu.contentreader;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends Activity {
    Uri URI =  Uri.parse("content://com.liqingliu.provider");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Cursor cursor = getContentResolver().query(URI,null,null,null,null);
        cursor.moveToFirst();
        for(int i = 0; i < cursor.getCount(); i++){
            String value = cursor.getString(cursor.getColumnIndex("name"));
            System.out.println("value: "+value);
            Toast.makeText(this,value,Toast.LENGTH_LONG).show();
        }
    }
}
