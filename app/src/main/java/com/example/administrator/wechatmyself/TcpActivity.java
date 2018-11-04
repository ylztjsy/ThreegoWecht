package com.example.administrator.wechatmyself;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.EditText;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

import util.DataHandleUtil;

public class TcpActivity {
    Socket sk;
    private OutputStream out = null;
    String user;
    String pwd;
    int i = 0;
    DataHandleUtil dataHandleUtil=new DataHandleUtil();

     public Socket up(final String user, final String pwd ) {
         this.user = user;
         this.pwd = pwd;

        //接收数据
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Log.i("线程", "启动线程");
                    InetAddress addr= InetAddress.getByName("192.168.1.101");
                    sk=new Socket(addr,9000);
                    Log.i("sk", sk.toString());

                    BufferedReader reader=new BufferedReader(new InputStreamReader(sk.getInputStream()));
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

return sk;
    }

    private int abc=0;
    //发送数据
    public void Log_in(final String user, final String pwd, final Socket sk1) {
        new Thread(){
            @Override
            public void run() {
                try {
                    Log.i("",user+"  "+pwd);
                    //Log.i("sk", sk);
                    out=sk1.getOutputStream();
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

    }
}
