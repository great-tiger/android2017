package com.example.liqingliu.learnsqllite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by liqingliu on 2017/11/7.
 */

public class Db extends SQLiteOpenHelper {

    public Db(Context context) {
        super(context, "Db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE User(" +
                        "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "name Text DEFAULT \"\"," +
                        "age TEXT DEFAULT \"\"" +
                     ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
