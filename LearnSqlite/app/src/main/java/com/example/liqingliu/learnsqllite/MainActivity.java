package com.example.liqingliu.learnsqllite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* 写入数据库
        Db db = new Db(this);
        SQLiteDatabase writableDatabase = db.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name","clh");
        values.put("age","20");
        writableDatabase.insert("User",null,values);
        values = new ContentValues();
        values.put("name","lql");
        values.put("age","20");
        writableDatabase.insert("User",null,values);
        writableDatabase.close();
        */

        /* 从数据库读取
        SQLiteDatabase readableDatabase = db.getReadableDatabase();
        Cursor user = readableDatabase.query("User", null, null, null, null, null, null);
        while (user.moveToNext()){
           String name =  user.getString(user.getColumnIndex("name"));
           String age = user.getString(user.getColumnIndex("age"));
            System.out.println("name: "+name +", age:"+age);
        }
        readableDatabase.close();
        */
    }
}
