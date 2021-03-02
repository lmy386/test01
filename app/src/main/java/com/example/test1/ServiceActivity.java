package com.example.test1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ServiceActivity extends AppCompatActivity {
    private Button mbtn;
    private TextView mtv;
    private static final int UPDATE_TEXT = 1;
    private Button sbtn1,sbtn2,sbtn3,sbtn4,sbtn5;
    private MyService.DownloadBinder downloadBinder;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            downloadBinder = (MyService.DownloadBinder)service;
            downloadBinder.startDownload();
            downloadBinder.getProgress();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case UPDATE_TEXT:
                    //进行相关操作
                    mtv.setText("这是点击之后新的文字！！！");
                    break;
                default:
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        mbtn = findViewById(R.id.service_btn);
        mtv = findViewById(R.id.service_tv);
        mbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message = new Message();
                        message.what = UPDATE_TEXT;
                        handler.sendMessage(message);
                    }
                }).start();
            }
        });

        //启动服务
        sbtn1 = findViewById(R.id.service_btn1);
        sbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent startIntent = new Intent(ServiceActivity.this,MyService.class);
                startService(startIntent);
            }
        });

        //停止服务
        sbtn2 = findViewById(R.id.service_btn2);
        sbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent stopIntent = new Intent(ServiceActivity.this,MyService.class);
                stopService(stopIntent);
            }
        });

        sbtn3 = findViewById(R.id.service_btn3);
        sbtn4 = findViewById(R.id.service_btn4);
        sbtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bindintent = new Intent(ServiceActivity.this,MyService.class);
                bindService(bindintent,serviceConnection,BIND_AUTO_CREATE);
            }
        });
        sbtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unbindService(serviceConnection);
            }
        });

        sbtn5 = findViewById(R.id.service_btn5);
        sbtn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //打印主线程ID
                Log.d("ServiceActivity", "Thread id is: " + Thread.currentThread().getId());
                //开启IntentService
                Intent intent = new Intent(ServiceActivity.this,MyIntentService.class);
                startService(intent);
            }
        });


    }
}