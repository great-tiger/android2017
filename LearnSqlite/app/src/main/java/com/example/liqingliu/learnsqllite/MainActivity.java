package com.example.liqingliu.learnsqllite;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private SimpleCursorAdapter cursorAdapter;
    private SQLiteDatabase readableDatabase;
    private SQLiteDatabase writableDatabase;
    private Button btn;
    private EditText etName;
    private EditText etAge;
    private Db db;
    private void refreshListView(){
        Cursor user = readableDatabase.query("User", null, null, null, null, null, null);
        //刷新列表
        cursorAdapter.changeCursor(user);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView =(ListView) findViewById(R.id.listview);
        btn = (Button) findViewById(R.id.save);
        etName = (EditText) findViewById(R.id.etName);
        etAge = (EditText) findViewById(R.id.etAge);
        db = new Db(this);
        readableDatabase = db.getReadableDatabase();
        writableDatabase = db.getWritableDatabase();

        Cursor user = readableDatabase.query("User", null, null, null, null, null, null);
        // 注意使用这个适配器时，数据库中需要有_id自增字段
        cursorAdapter = new SimpleCursorAdapter(this,R.layout.list_view_cell,user,new String[]{"name","age"},new int[]{R.id.name,R.id.age});
        listView.setAdapter(cursorAdapter);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();
                values.put("name",etName.getText().toString());
                values.put("age",etAge.getText().toString());
                System.out.print(etName.getText().toString() + ":" + etAge.getText().toString());
                writableDatabase.insert("User",null,values);
                refreshListView();
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,final int position, long id) {
                new AlertDialog.Builder(MainActivity.this).setTitle("提醒")
                        .setMessage("确定要删除该项吗？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Cursor c = cursorAdapter.getCursor();
                                c.moveToPosition(position);
                                int itemId = c.getInt(c.getColumnIndex("_id"));
                                writableDatabase.delete("User","_id=?",new String[]{itemId+""});
                                refreshListView();
                            }
                        })
                        .setNegativeButton("取消",null)
                        .show();
                //表示此次长按操作是否成功。有的系统操作成功会给用户反馈
                return true;
            }
        });

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
