package com.example.administrator.wechatmyself;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import bean.Friend;

public class FriendsAdapter extends BaseAdapter {
    private Context con;
    private List<Friend> list;
    private Friend friend;


    public FriendsAdapter(Context conn, List<Friend> list){
        this.con=conn;
        this.list=list;

    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh=null;
        if(convertView==null){
            vh=new ViewHolder();
            convertView= LayoutInflater.from((Context) con).inflate(R.layout.friend_items,null);
            vh.img=(ImageView) convertView.findViewById(R.id.img);
            vh.text_message=(TextView)convertView.findViewById(R.id.text_message);
            vh.text_name=(TextView)convertView.findViewById(R.id.text_name);
            convertView.setTag(vh);
        }else{
            vh=(ViewHolder)convertView.getTag();

        }
        friend=list.get(position);
        vh.img.setColorFilter(Color.blue(1));
        vh.text_message.setText(friend.getProvince()+" "+friend.getCity());
        vh.text_name.setText(friend.getName());
//convertView.setBackgroundColor(R.drawable.red);
        return convertView;



    }


    public class ViewHolder {
        //所有控件对象引用

        public ImageView img;		//音乐标题
        public TextView text_name;	//用户昵称
       public TextView text_message;
    }


}
