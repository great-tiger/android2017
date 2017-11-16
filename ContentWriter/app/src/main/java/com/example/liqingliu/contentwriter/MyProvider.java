package com.example.liqingliu.contentwriter;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.Settings;

/**
 * Created by liqingliu on 2017/11/16.
 */

public class MyProvider extends ContentProvider {

    public static final Uri URI = Uri.parse("content://com.liqingliu.provider");
    SQLiteDatabase database;

    @Override
    public boolean onCreate() {
        database = getContext().openOrCreateDatabase("my.db", Context.MODE_PRIVATE,null);
        database.execSQL("create table tab(_id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT NOT NULL)");
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] strings, String s, String[] strings1, String s1) {
        Cursor cursor = database.query("tab",null,null,null,null,null,null);
        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        System.out.println("insert uri: "+uri.toString());
        database.insert("tab",null,contentValues);
        return null;
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        return 0;
    }
}
