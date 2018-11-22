package com.example.jingdong.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jingdong.R;
import com.example.jingdong.bean.RegisBean;
import com.example.jingdong.presenter.RegisPresenter;
import com.example.jingdong.view.iview.RegisView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisActivity extends AppCompatActivity implements RegisView {

    @BindView(R.id.reg_name)
    EditText regName;
    @BindView(R.id.reg_pass)
    EditText regPass;
    @BindView(R.id.reg_btn)
    Button regBtn;

    private RegisPresenter regisPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regis);
        ButterKnife.bind(this);

        regisPresenter = new RegisPresenter(this);
    }

    @OnClick({R.id.reg_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.reg_btn:
                String name = regName.getText().toString();
                String password = regPass.getText().toString();
                regisPresenter.register(name,password);
                break;
        }
    }

    @Override
    public void onRegSuccess(RegisBean regisBean) {
        Toast.makeText(this, regisBean.getMsg(), Toast.LENGTH_SHORT).show();
        if (regisBean.getMsg().equals("注册成功")){
            Intent intent1=new Intent(RegisActivity.this,LoginActivity.class);
            startActivity(intent1);
            finish();
        }
    }

    @Override
    public void onErrorReg(RegisBean regisBean) {

    }
}
