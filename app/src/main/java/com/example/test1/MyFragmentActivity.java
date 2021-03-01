package com.example.test1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.lang.reflect.Field;

public class MyFragmentActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    //定义Fragment
    private Fragment_One oneFragment;
    private Fragment_Two fragment_two;
    private Fragment_Three fragment_three;
    //记录当前正在使用的fragment
    private Fragment isFragment;

    private IntentFilter intentFilter;
    private IntentFilter intentFilter1;
    private NetworkChangeReceiver networkChangeReceiver;
    private MyBroadcastReceiver myBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_fragment);
        //初始化Fragment及底部导航栏
        initFragment(savedInstanceState);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNav);
        //关闭底部导航栏默认动画效果并添加监听器
        //disableShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        //接受系统广播
        intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        networkChangeReceiver = new NetworkChangeReceiver();
        registerReceiver(networkChangeReceiver,intentFilter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消注册
        unregisterReceiver(networkChangeReceiver);
    }



    public void initFragment(Bundle savedInstanceState) {
        //判断activity是否重建，如果不是，则不需要重新建立fragment.
        if (savedInstanceState == null) {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            if (oneFragment == null) {
                oneFragment = new Fragment_One();
            }
            isFragment = oneFragment;
            ft.replace(R.id.container, oneFragment).commit();
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_xiaoxi:
                    if (oneFragment == null) {
                        oneFragment = new Fragment_One();
                    }
                    switchContent(isFragment, oneFragment);
                    return true;
                case R.id.navigation_lianxiren:
                    if (fragment_two == null) {
                        fragment_two = new Fragment_Two();
                    }
                    switchContent(isFragment, fragment_two);
                    return true;
                case R.id.navigation_shezhi:
                    if (fragment_three == null) {
                        fragment_three = new Fragment_Three();
                        //创建Bundle对象作用:存储数据，并传递到Fragment中
                        Bundle bundle = new Bundle();
                        // 往bundle中添加数据
                        bundle.putString("message", "这是Activity传给fragment的信息");
                        // 步骤6:把数据设置到Fragment中
                        fragment_three.setArguments(bundle);
                        fragment_three.sendMessage(new ICallBack() {
                            @Override
                            public void get_message_from_Fragment(String string) {
                                Log.e("yangling", string);
                            }
                        });
                    }
                    switchContent(isFragment, fragment_three);
                    return true;
            }
            return false;
        }

    };


    public void switchContent(Fragment from, Fragment to) {
        if (isFragment != to) {
            isFragment = to;
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            if (!to.isAdded()) {    // 先判断是否被add过
                ft.hide(from).add(R.id.container, to).commit(); // 隐藏当前的fragment，add下一个到Activity中
            } else {
                ft.hide(from).show(to).commit(); // 隐藏当前的fragment，显示下一个
            }
        }
    }


    //新建一个NetworkChangeReceiver继承BroadcastReceivier,重写里面的onReceiver方法
    class NetworkChangeReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            //获取网络状态
            ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isAvailable()){
                Toast.makeText(context, "network is available", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(context, "network is unavailable", Toast.LENGTH_SHORT).show();
            }

        }
    }



}