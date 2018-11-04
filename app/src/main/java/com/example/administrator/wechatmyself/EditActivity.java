package com.example.administrator.wechatmyself;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EditActivity extends AppCompatActivity {
    AllMethod am=new AllMethod();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
    }

    Button save=findViewById(R.id.save);
    public void Save(View view) {
        save = findViewById(R.id.save);
        TextView phone = findViewById(R.id.Phone);
        TextView wxnumber = findViewById(R.id.wxnumber);
        TextView address = findViewById(R.id.Address);
        int result = am.edit(phone.getText().toString(),wxnumber.getText().toString(),address.getText().toString());
        if( result == 1 ){
            showTips("保存成功");
            Intent intent = new Intent(EditActivity.this, MyInfoActivity.class);
            startActivity(intent);
        }else{
            showTips("服务器出错啦，请稍等~~~");
        }
    }


    //弹框
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
