package com.example.administrator.wechatmyself;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;

import util.DataHandleUtil;

public class LoginActivity extends AppCompatActivity {
    private Button btn_register;
    private Button btn_login;
    public static String userID;
    public static Socket sk;
    DataHandleUtil dataHandleUtil=new DataHandleUtil();


    AllMethod am = new AllMethod();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        initView();
        initEvent();


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Log.i("线程", "启动线程");
                  InetAddress addr= InetAddress.getByName("59.48.241.18");

                    sk=new Socket(addr,9000);
                    Log.i("sk", sk.toString());

                    BufferedReader reader=new BufferedReader(new InputStreamReader(sk.getInputStream()));
//                    while(true)
//                    {
                        String resv=reader.readLine();
                        if(resv != null)
                        {
                            Log.i("","  "+resv);
                            userID=dataHandleUtil.loginResponse(resv);
                            Log.i("","  "+LoginActivity.userID);
                        }
//                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void initView() {
        btn_register = findViewById(R.id.btn_register);
    }

    private void initEvent() {
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TextView
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }


    public void Login(View view) {
        btn_login = findViewById(R.id.btn_login);
        TextView user = findViewById(R.id.tv_username);
        TextView pwd = findViewById(R.id.tv_password);
        Log.i("nmh","cnm"+user.getText().toString());
        if ((user.getText().toString().equals("") ) || (pwd.getText().toString().equals(""))) {
            showTips("用户名或密码不能为空！");
        } else {
            int result = am.checkLogin(user.getText().toString(), pwd.getText().toString(),sk);
            if (result == 1) {
                Intent intent = new Intent(LoginActivity.this, FriendActivity.class);
                startActivity(intent);
            } else {
                showTips("用户名或密码不正确！");
            }
        }
    }

    private void showTips(String str){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        // builder.setIcon(R.drawable.picture);
        builder.setTitle("温馨提示");
        builder.setMessage(str);
        builder.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        AlertDialog dialog=builder.create();
        dialog.show();

    }
}

