package com.example.test1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button mbtn;
    String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //找到控件
        mbtn = findViewById(R.id.main_btn);
        mbtn.setText("点击开始了吗");
        //给mbtn设置点击监听
        mbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"点击了Button，即将跳转",Toast.LENGTH_SHORT).show();
                //点击之后跳转到login
                Intent intent = new Intent(MainActivity.this,login.class);
                intent.putExtra("values","hello LoginActivity");
                startActivity(intent);
               // finish();
            }
        });
        Button mbtn1 = findViewById(R.id.main_btn1);
        mbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.baidu.com"));
                startActivity(intent);
            }
        });
        //进行初始化操作，加载布局、绑定事件等
        Log.i(TAG, "onCreate: ");



    }

    @Override
    protected void onStart() {
        super.onStart();
        //由活动不可见变为可见的时候调用
        Log.i(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        //准备和用户进行交互的时候调用，此时活动位于栈顶并处于运行状态
        Log.i(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        //去启动或恢复另一个活动的时候调用
        Log.i(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        //活动完全不可见的时候调用
        Log.i(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //活动被销毁之前调用
        Log.i(TAG, "onDestroy: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        //活动由停止状态变为运行状态之前调用
        Log.i(TAG, "onRestart: ");
    }
}