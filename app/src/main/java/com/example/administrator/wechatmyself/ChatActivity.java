package com.example.administrator.wechatmyself;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bean.ChatContent;
import bean.Friend;
import util.DataHandleUtil;

public class ChatActivity extends AppCompatActivity{

    private Button mBtnSend;// 发送btn
    private ImageView mBtnBack;// 返回btn
    private EditText mEditTextContent;
    private TextView mListView;
    private ChatMsgViewAdapter mAdapter;// 消息视图的Adapter
    private List<ChatMsgEntity> mDataArrays = new ArrayList<ChatMsgEntity>();// 消息对象数组
    private DataHandleUtil dataHandleUtil;
    private int other_id = -1;
    private OutputStream output;
    private InputStream input;

    private String res1;
    private String res2;
    private ChatMsgViewAdapter chatMsgViewAdapter;
    //private DBManager dbManager;
    private ChatMsgEntity entity;
    private EditText text;
    private Button bt;
    private RecyclerView recy;
    private Handler handler;
    Button send;
    TextView content1;
    public List list;
    List<ChatContent> chatContents = null;
    ChatContent cc1;
    AllMethod am = new AllMethod();
    String content;

    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);//设置窗口没有标题栏
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat);

        list = new ArrayList<xx>();
        text = findViewById(R.id.text);

        mListView=findViewById(R.id.listview);
        LinearLayoutManager lin = new LinearLayoutManager(this);
        //recy.setLayoutManager(lin);
        mEditTextContent=findViewById(R.id.et_sendmessage);
        send = findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {//给发送按钮设置监听事件
            @Override
            public void onClick(View v) {
                Log.i("1", "点击触发");
                List<ChatContent> list = new ArrayList<ChatContent>();
                String contString = mEditTextContent.getText().toString();
                ChatContent cc = new ChatContent();
                Date dt = new Date();
                cc.setCid(0);
                cc.setContent(contString);
                cc.setInsertTime(dt.getTime());
                cc.setIsMine(1);
                cc.setState(0);
                list.add(cc);
                Log.i("1", "调用前 ");
                am.sending(list);
                String str="我：\n"+mEditTextContent.getText().toString()+"\n";
                mEditTextContent.setText("");// 清空编辑框数据
                mListView.setText(mListView.getText().toString()+str);
//                mListView.setSelection(mListView.getCount() - 1);// 发送一条消息时，ListView显示选择最后一项

            }


        });


        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
//                cc1.getCid();
//                cc1.getContent();
//                cc1.getInsertTime();
//                chatMsgViewAdapter = new ChatMsgViewAdapter(getApplicationContext(), mDataArrays);
//                mListView.setAdapter(chatMsgViewAdapter);
//                mListView.setSelection(mListView.getCount() - 1);// 发送一条消息时，ListView显示选择最后一项
//                content1 = findViewById(R.id.content);
//                content1.setText(content + "\n");
                String str="对方：\n"+content+"\n";
                mEditTextContent.setText("");// 清空编辑框数据
                mListView.setText(mListView.getText().toString()+str);
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

                    BufferedReader reader = new BufferedReader(new InputStreamReader(LoginActivity.sk.getInputStream()));
//                    while(true)
//                    {
                    String resv = reader.readLine();
                    Log.i("", "3213123" + resv);
                    if (resv != null) {
                        System.out.println(resv);
                        Log.i("", "  " + resv);
                        Log.i("sssssss", "  " + chatContents);
//                        cc1=chatContents.get(0);
                        JSONObject objs = new JSONObject(resv);
                        String main = objs.getString("main");
                        JSONObject objmain = new JSONObject(main);
                        String list = objmain.getString("list");
                        JSONObject ojbStr = new JSONObject(list);
                        content = ojbStr.getString("content");
                        Message msg = Message.obtain();
                        handler.handleMessage(msg);
                    }
//                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();



        Intent intent = getIntent();
        other_id = intent.getIntExtra("other_id", other_id);

    }
        //初始化view
        public void initView () {
            mListView = (TextView) findViewById(R.id.listview);
            mBtnSend = (Button) findViewById(R.id.send);
            mBtnSend.setOnClickListener((View.OnClickListener) this);
            mBtnBack = (ImageView) findViewById(R.id.btn_back);
            mBtnBack.setOnClickListener((View.OnClickListener) this);
            mEditTextContent = (EditText) findViewById(R.id.et_sendmessage);
        }


//        public void onClick (View v){
//            switch (v.getId()) {
//                case R.id.btn_send:// 发送按钮点击事件
//                    try {
//                        send();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                        Log.i("发送", "发送失败");
//                    }
//                    break;
//                case R.id.btn_back:// 返回按钮点击事件
//                    finish();// 结束,实际开发中，可以返回主界面
//                    break;
//            }
//        }

        //发送消息


        //发送消息时，获取当前事件，当前时间
        public String getDate () {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
            return format.format(new Date());
        }


}

