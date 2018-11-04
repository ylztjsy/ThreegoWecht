package com.example.administrator.wechatmyself;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;

public class RegisterActivity extends AppCompatActivity {
    private Button btn_loging;

    EditText phone ;
    EditText name ;
    EditText pwd ;
    AllMethod am = new AllMethod();
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);
        btn_loging = findViewById(R.id.btn_loging);
        phone = findViewById(R.id.register_phone);
        name = findViewById(R.id.register_name);
        pwd = findViewById(R.id.register_password);


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Log.i("线程", "启动线程");
                    InetAddress addr= InetAddress.getByName("192.168.1.101");
                    LoginActivity.sk=new Socket(addr,9000);
                    Log.i("sk", LoginActivity.sk.toString());

                    BufferedReader reader=new BufferedReader(new InputStreamReader(LoginActivity.sk.getInputStream()));
                    while(true)
                    {
                        String resv=reader.readLine();
                        if(resv != null)
                        {
                            Log.i("","  "+resv);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();




        Register();





    }

    public void Register() {
        //Log.i("nmh","cnm"+user.getText().toString());
        btn_loging.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((phone.getText().toString().equals("") ) || (pwd.getText().toString().equals(""))||(name.getText().toString().equals(""))) {
                    showTips("昵称或密码或手机号不能为空！");
                } else {
                    int result = am.checkRegister(phone.getText().toString(), pwd.getText().toString(),name.getText().toString(),LoginActivity.sk);
                    if (result == 1) {
                        showTips("注册成功！");
                        Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                        startActivity(intent);
                    } else {
                        showTips("注册失败！");
                    }
                }
            }
        });

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


