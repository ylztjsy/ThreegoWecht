package com.example.administrator.wechatmyself;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.Friend;
import util.DataHandleUtil;

public class FriendActivity extends Activity {
    private ListView group_tv = null;
    private FriendsAdapter fa;
    public static String FriendID;
    AllMethod am=new AllMethod();
    List<Friend> friends=null;
    private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend);

        am.checkMyFriends();
        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                group_tv = (ListView) findViewById(R.id.group_tv);

                fa = new FriendsAdapter(getApplicationContext(),friends);
                group_tv.setAdapter(fa);

                group_tv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                         String UserID=LoginActivity.userID;//用户ID
                                FriendID=friends.get(position).getId()+"";//聊天对象的ID
                                Intent intent = new Intent(FriendActivity.this,ChatActivity.class);
                                startActivity(intent);

                    }
                });
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
                    Log.i("","3213123"+resv);
                    if(resv != null)
                    {
                        Log.i("","  "+resv);
                        friends= new DataHandleUtil().getFriendListResponse(resv);
                        Log.i("sssssss","  "+friends);


                        Message msg=Message.obtain();
                        handler.handleMessage(msg);
                    }
//                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();




    }

    public void ShowMyInfo(View view) {
        am.checkMyInfo();

        Intent intent = new Intent(FriendActivity.this,MyInfoActivity.class);
        startActivity(intent);
    }

    public void showFriends(View view) {
        am.showFriends();

        Intent intent = new Intent(FriendActivity.this,FriendActivity.class);
        startActivity(intent);
    }
}