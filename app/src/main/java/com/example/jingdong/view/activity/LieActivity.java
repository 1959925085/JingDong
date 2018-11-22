package com.example.jingdong.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.jingdong.R;
import com.example.jingdong.bean.LieBean;
import com.example.jingdong.presenter.LiePresenter;
import com.example.jingdong.view.adapter.LieAdapter;
import com.example.jingdong.view.iview.LieView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LieActivity extends AppCompatActivity implements LieView {

    @BindView(R.id.lie_recyclerView)
    RecyclerView lieRecyclerView;

    private LiePresenter liePresenter;
    private String name;
    private List<LieBean.DataBean> data;
    private LieAdapter lieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lie);
        ButterKnife.bind(this);

        liePresenter = new LiePresenter(this);
        Intent intent = getIntent();
        name = intent.getStringExtra("name");

        liePresenter.getlie(name,1);
        lieRecyclerView.setLayoutManager(new LinearLayoutManager(LieActivity.this,LinearLayoutManager.VERTICAL,false));
    }

    @Override
    public void onSuccess(LieBean lieBean) {
        Log.e("ggggg",lieBean.getData()+"");
        data = lieBean.getData();
        lieAdapter = new LieAdapter(LieActivity.this, data);
        lieRecyclerView.setAdapter(lieAdapter);
    }
}
