package com.example.test1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class Fragment_Three extends Fragment {
    private TextView textView;
    private TextView textView2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.fragment__three, container, false);
        // 设置布局文件
        textView = (TextView) contentView.findViewById(R.id.ft_tv);
        textView2 = (TextView) contentView.findViewById(R.id.ft_tv2);

        // 步骤1:通过getArgments()获取从Activity传过来的全部值
        Bundle bundle = this.getArguments();

        // 步骤2:获取某一值
        String message = bundle.getString("message");
        textView.setText(message);
        return contentView;
    }

    // 设置 接口回调 方法
    public void sendMessage(ICallBack callBack){

        callBack.get_message_from_Fragment("我是来自Fragment的信息");

    }
}