package com.example.administrator.wechatmyself;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import java.util.List;

import bean.Friend;
import util.DataHandleUtil;

public class MyInfoActivity extends AppCompatActivity {
    private Button edit;
    private Button exit;
    private TextView mytext;
    private TextView PhoneNum;
    private TextView WeChatNum;
    private TextView Address;
    private Handler handler;
    AllMethod cm = new AllMethod();
    Friend friend=new Friend();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_info);

         mytext= (TextView) findViewById(R.id.mytext);
         PhoneNum= findViewById(R.id.PhoneNum);
          WeChatNum= findViewById(R.id.WeChatNum);
          Address= findViewById(R.id.Address);

          handler=new Handler(){
              @Override
              public void handleMessage(Message msg) {
                  Log.i("gggg","jinlaile  "+friend.getName()+" "+friend.getMcode()+"  "+friend.getProvince()  );
                    mytext.setText(friend.getName());
                    PhoneNum.setText(friend.getPhoneNum());
                    WeChatNum.setText(friend.getMcode());
                    Address.setText(friend.getProvince()+" "+friend.getCity());
              }
          };
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
//                    Log.i("线程", "启动线程");
//                    InetAddress addr= InetAddress.getByName("192.168.1.101");
//                    LoginActivity.sk=new Socket(addr,9000);
//                    Log.i("sk", LoginActivity.sk.toString());

                    BufferedReader reader=new BufferedReader(new InputStreamReader(LoginActivity.sk.getInputStream()));
//                    while(true)
//                    {
                        String resv=reader.readLine();
                        Log.i("","123456"+resv);
                        if(resv != null)
                        {
                            Log.i("","  "+resv);
                            List<Friend> list= new DataHandleUtil().getFriendListResponse(resv);
                            Log.i("sssssss","  "+list);



                            for (int i=0;i<list.size();i++){
                                if (list.get(i).getId()== Integer.parseInt( LoginActivity.userID)){
                                    friend=list.get(i);
                                }
                            }
                            Message msg=Message.obtain();
                            handler.handleMessage(msg);
                        }
//                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();


       // Friend friend = new Friend();
        //friend = cm.StringToFriend();
//        mytext.setText(friend.getName());
//        PhoneNum.setText(friend.getPhoneNum());
//        WeChatNum.setText(friend.getMcode());
//        Address.setText(friend.getProvince()+" "+friend.getCity());
    }

    public void Edit(View view) {
        edit = findViewById(R.id.edit);
        Intent intent = new Intent(MyInfoActivity.this, SaveActivity.class);
        startActivity(intent);
    }


    public void Exit(View view) {
        exit = findViewById(R.id.exit);
        Intent intent = new Intent(MyInfoActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}
