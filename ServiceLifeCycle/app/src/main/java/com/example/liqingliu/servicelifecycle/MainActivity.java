/**
 * 简单理解StartService的生命周期
 * onCreate -> onStartCommand -> onDestroy
 * BindService的生命周期
 * onCreate -> onBind -> onUnbind -> onDestroy
 *
 */
package com.example.liqingliu.servicelifecycle;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {

    private Button startBtn,stopBtn,bindBtn,unbindBtn;
    private boolean bounded = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startBtn = findViewById(R.id.startBtn);
        stopBtn = findViewById(R.id.stopBtn);
        bindBtn = findViewById(R.id.bindBtn);
        unbindBtn = findViewById(R.id.unbindBtn);

        startBtn.setOnClickListener(this);
        stopBtn.setOnClickListener(this);
        bindBtn.setOnClickListener(this);
        unbindBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(this,HelloService.class);
        switch (v.getId()) {
            case R.id.startBtn:
                startService(i);
                break;
            case R.id.stopBtn:
                stopService(i);
                break;
            case R.id.bindBtn:
                bindService(i,serviceConnection, Context.BIND_AUTO_CREATE);
                break;
            case R.id.unbindBtn:
                if (bounded) {
                    unbindService(serviceConnection);
                }
                break;
        }
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            bounded = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            bounded = false;
        }
    };
}
