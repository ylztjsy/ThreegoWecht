package com.example.administrator.wechatmyself;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;

import bean.ChatContent;
import bean.Friend;
import util.DataHandleUtil;

import static com.example.administrator.wechatmyself.LoginActivity.sk;

public class AllMethod {
    private OutputStream out = null;
    int abc=0;
    DataHandleUtil dataHandleUtil=new DataHandleUtil();



    public int checkLogin(final String user, final String pwd, final Socket sk){
        int result = 1;
        new Thread(){
            @Override
            public void run() {
                try {
                    Log.i("",user+"  "+pwd);
                    //Log.i("sk", sk);
                    out=sk.getOutputStream();
                    //out.write((data.toString() + '\n').getBytes()) ;
                    //out.write("Hello world\n".getBytes());
//                    String test = dataHandleUtil.registerRequest("153","Jack","123");
                    if(abc==0){
                        String test = dataHandleUtil.loginRequest(user,pwd);
//                    List<ChatContent> chatContents=new ArrayList<>();
//                    ChatContent chatContent=new ChatContent();
//                    chatContent.setCid();
//                    String test = dataHandleUtil.sendChatContentRequest("1",);
                        out.write((test + "\n").getBytes());
                        abc++;

                    }else if(abc==1){
                        String test = dataHandleUtil.getFriendCircleRequest(2,1);
                        out.write((test + "\n").getBytes());
                        abc++;
                    }else {
                        String test = dataHandleUtil.getFriendListRequest(1);
                        out.write((test + "\n").getBytes());

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();




       // tcp tcp = new tcp();
        //tcp.Log_in(user,pwd,sk);
       return result;
    }
    public int edit(String phone,String wxnumber,String address){
        int result = 1;




        //放zcy的方法；
        return result;
    }

    public Friend StringToFriend(){
        Friend friend = new Friend();
        // ZCY     friend =
        return friend;
    }

    public int checkRegister(final String phone, final String name, final String pwd, final Socket sk){
        int result = 1;

        new Thread(){
            @Override
            public void run() {
                try {
                    //Log.i("sk", sk);
                    out=sk.getOutputStream();
                    //out.write((data.toString() + '\n').getBytes()) ;
                    //out.write("Hello world\n".getBytes());
//                    String test = dataHandleUtil.registerRequest("153","Jack","123");
                    if(abc==0){
                        String test = dataHandleUtil.registerRequest(phone,name,pwd);
//                    List<ChatContent> chatContents=new ArrayList<>();
//                    ChatContent chatContent=new ChatContent();
//                    chatContent.setCid();
//                    String test = dataHandleUtil.sendChatContentRequest("1",);
                        out.write((test + "\n").getBytes());
                        abc++;

                    }else if(abc==1){
                        String test = dataHandleUtil.getFriendCircleRequest(2,1);
                        out.write((test + "\n").getBytes());
                        abc++;
                    }else {
                        String test = dataHandleUtil.getFriendListRequest(1);
                        out.write((test + "\n").getBytes());

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();



        //调方法
        return result;
    }

    public void checkMyInfo() {
        int result = 1;
        new Thread() {
            @Override
            public void run() {
                try {
                    Log.i("out", out.toString());
                    out = LoginActivity.sk.getOutputStream();
                    Log.i("s","  "+LoginActivity.userID);
                    String test = dataHandleUtil.getFriendListRequest(Integer.parseInt(LoginActivity.userID));
                    out.write((test + "\n").getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        //调方法
        //return ;
    }


    public void showFriends() {
        new Thread() {
            @Override
            public void run() {
                try {
                    out = LoginActivity.sk.getOutputStream();
                    Log.i("s","  "+LoginActivity.userID);
                    String test = dataHandleUtil.getFriendListRequest(Integer.parseInt(LoginActivity.userID));
                    out.write((test + "\n").getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();



    }

    public void checkMyFriends() {
        new Thread() {
            @Override
            public void run() {
                try {
                    out = LoginActivity.sk.getOutputStream();
                    Log.i("s","  "+LoginActivity.userID);
                    String test = dataHandleUtil.getFriendListRequest(Integer.parseInt(LoginActivity.userID));
                    out.write((test + "\n").getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();


    }

    public void sending(final List<ChatContent> content) {
        new Thread() {
            @Override
            public void run() {
                try {
                    out = LoginActivity.sk.getOutputStream();
                    Log.i("s","  "+LoginActivity.userID);
                    String test = dataHandleUtil.sendChatContentRequest(Integer.parseInt(LoginActivity.userID),Integer.parseInt(FriendActivity.FriendID),content);

                    out.write((test + "\n").getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();



    }
}

