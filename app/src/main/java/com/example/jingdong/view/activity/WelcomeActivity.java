package com.example.jingdong.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.jingdong.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WelcomeActivity extends AppCompatActivity {

    @BindView(R.id.text_welcome)
    TextView textWelcome;

    //定义初始值
    private int time = 3;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0){
                time--;
                textWelcome.setText("");
                if (time == 0){
                    //跳转
                    Intent intent = new Intent(WelcomeActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    //延时发送消息
                    handler.sendEmptyMessageDelayed(0,1000);
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);

        //延迟跳转
        handler.sendEmptyMessageDelayed(0,1000);
    }
}
