package com.example.test1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class login extends AppCompatActivity {
    String TAG = "loginActivity";
    private EditText edt_username;
    private EditText edt_password;
    private Button btn_login;
    private ImageButton mimageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Log.i(TAG, "onCreate: ");
        edt_username = findViewById(R.id.login_edt_username);
        edt_password = findViewById(R.id.login_edt_passwprd);
        btn_login = findViewById(R.id.login_btn);
        mimageButton = findViewById(R.id.imagebutton);
        //给登录按钮设置监听
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = edt_username.getText().toString();
                String passWord = edt_password.getText().toString();
                if (userName.equals("admin")&&passWord.equals("123456")){
                    Intent intent = new Intent(login.this,MyFragmentActivity.class);
                    startActivity(intent);
                    finish();
                }
                else Toast.makeText(login.this, "请输入正确的用户名和密码", Toast.LENGTH_SHORT).show();

            }
        });

        //给ImageButton设置监听
        mimageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(login.this, "你点击了ImageButton", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart: ");
    }
}