package com.example.huanxindemo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.easemob.chat.EMMessage;

public class MainActivity extends AppCompatActivity {

    private EditText name,pwd,toname,message,filename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = (EditText) findViewById(R.id.name);//用户名输入框
        pwd = (EditText) findViewById(R.id.pwd);//密码输入框
        toname = (EditText) findViewById(R.id.toname);//收消息的对象
        message = (EditText) findViewById(R.id.message);//发送的内容
        filename = (EditText) findViewById(R.id.filename);//发送的内容
    }

    /**
     * 登录 发送消息 记住要判空,判断内容是否为空
     * @param view
     */
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login:
                Intent intent = new Intent(this, HuanXinService.class);
                intent.putExtra("name", name.getText().toString());
                intent.putExtra("pwd", pwd.getText().toString());
                startService(intent);
                break;
            case R.id.send:
                HuanXinUtils.sendMessage(toname.getText().toString(), message.getText().toString(), EMMessage.Type.TXT,0);
                break;
            case R.id.sendfile:
                String sendFileName = filename.getText().toString();
                String filePath = Environment.getExternalStorageDirectory() + "/" + sendFileName + ".txt";
                HuanXinUtils.sendMessage(toname.getText().toString(), filePath, EMMessage.Type.FILE, 0);
                break;
        }
    }
}
