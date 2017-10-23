package com.example.liqingliu.test;

import android.app.Activity;
import android.app.IntentService;
import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Messenger messenger;
        Message message = new Message();
        Handler handler = new Handler();
        handler.sendEmptyMessage(1);
        IntentService service;
        HandlerThread thread;
        Message.obtain(message);
        Looper.prepare();
        Looper.loop();
        handler.dispatchMessage(message);
        super.onCreate(savedInstanceState);
    }

}