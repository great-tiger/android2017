package com.example.liqingliu.servicelifecycle;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class HelloService extends Service {
    private static final String TAG = "HelloService";
    public HelloService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG,"onBind");
        return new MyBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG,"onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG,"onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG,"onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"onDestroy");
    }
}
