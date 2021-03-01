package com.example.test1;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class Fragment_Two extends Fragment {
    Button mbtn;
    private MyBroadcastReceiver myBroadcastReceiver;
    private LocalBroadcastManager localBroadcastManager;
    IntentFilter intentFilter1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //引入布局
        View view = inflater.inflate(R.layout.fragment__two,container,false);
        mbtn = view.findViewById(R.id.fragment_two_btn);
        localBroadcastManager = LocalBroadcastManager.getInstance(getActivity());
        mbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.test1.MY_BROADCAST");
                localBroadcastManager.sendBroadcast(intent);
            }
        });
        //接收标准广播

        intentFilter1 = new IntentFilter();
        intentFilter1.addAction("com.example.test1.MY_BROADCAST");
        myBroadcastReceiver = new MyBroadcastReceiver();
        localBroadcastManager.registerReceiver(myBroadcastReceiver,intentFilter1);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onStart() {
        super.onStart();

    }

}