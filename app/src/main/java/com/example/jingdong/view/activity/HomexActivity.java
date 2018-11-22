package com.example.jingdong.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jingdong.R;
import com.example.jingdong.bean.AddShopBean;
import com.example.jingdong.bean.HomexBean;
import com.example.jingdong.bean.ShopBean;
import com.example.jingdong.presenter.HomexPresenter;
import com.example.jingdong.presenter.ShopPresenter;
import com.example.jingdong.view.iview.HomexView;
import com.example.jingdong.view.iview.ShopView;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomexActivity extends AppCompatActivity implements HomexView, ShopView {

    @BindView(R.id.home_sdv_icon)
    SimpleDraweeView homeSdvIcon;
    @BindView(R.id.home_tv_title)
    TextView homeTvTitle;
    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.home_btn_add)
    Button homeBtnAdd;
    @BindView(R.id.home_btn_kefu)
    Button homeBtnKefu;

    private HomexPresenter homexPresenter;
    private ShopPresenter shopPresenter;

    private int pid;
    private SharedPreferences sp;
    private String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homex);
        ButterKnife.bind(this);

        homexPresenter = new HomexPresenter(this);
        pid = getIntent().getIntExtra("pid", 1);
        homexPresenter.xiangqing(pid);

        sp = getSharedPreferences("flag", Context.MODE_PRIVATE);
        uid = sp.getString("uid", "1");

        shopPresenter = new ShopPresenter(this);

    }

    @Override
    public void resultData(HomexBean homexBean) {
        String s = homexBean.getData().getTitle();
        homeTvTitle.setText(s);
        price.setText("￥" + homexBean.getData().getPrice());
        homeSdvIcon.setImageURI(homexBean.getData().getImages().split("\\|")[0]);

    }

    @Override
    public void onSuccesss(ShopBean shopBean) {

    }

    @Override
    public void onAdd(AddShopBean addShopBean) {
//
        if (addShopBean.getMsg().equals("加购成功")) {
            Toast.makeText(this, addShopBean.getMsg(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onErrorr(Throwable t) {

    }

    @OnClick({R.id.home_btn_kefu, R.id.home_btn_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.home_btn_kefu:
                Intent intent = new Intent(HomexActivity.this,DataActivity.class);
                startActivity(intent);
                break;
            case R.id.home_btn_add:

                if (uid!=null&&!"1".equals(uid)){
                    shopPresenter.addshop(String.valueOf(pid),uid);
                }else {
                    Toast.makeText(this,"请先登录", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
