package com.example.administrator.wechatmyself;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

//import adapter.FriendCircleAdapter;
import bean.Friend;
import bean.FriendCircle;
import util.DataHandleUtil;

public class FriendCircleActivity extends AppCompatActivity {
    private ListView friend_list_circle;
    private ArrayAdapter adapter;
    Socket sk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_circle);
        friend_list_circle=(ListView)findViewById(R.id.friend_list_circle);


        //在这里改
//        List<FriendCircle> friendCircles=new ArrayList<>();
//        FriendCircle friendCircle=new FriendCircle();
//        friendCircle.setContent("哈哈哈！！！");
//        friendCircles.add(friendCircle);
//        adapter=new FriendCircleAdapter(FriendCircleActivity.this,R.layout.friend_circle_items,friendCircles);

    }
}
