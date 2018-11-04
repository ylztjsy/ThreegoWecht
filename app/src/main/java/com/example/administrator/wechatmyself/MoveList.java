package com.example.administrator.wechatmyself;


import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class MoveList extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friend_items);

        Bundle bundle=getIntent().getExtras();
        int id=bundle.getInt("photo");
        String message=bundle.getString("message");
        ImageView img=(ImageView) findViewById(R.id.img);
        img.setImageResource(id);
        TextView text_message=(TextView) findViewById(R.id.text_message);
        text_message.setText(message);

    }






}

