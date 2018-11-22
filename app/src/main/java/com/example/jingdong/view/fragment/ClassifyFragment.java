package com.example.jingdong.view.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.jingdong.R;
import com.example.jingdong.bean.RightBean;
import com.example.jingdong.bean.ShouBean;
import com.example.jingdong.presenter.RightPresenter;
import com.example.jingdong.presenter.ShouPresenter;
import com.example.jingdong.view.activity.SouActivity;
import com.example.jingdong.view.adapter.LeftAdapter;
import com.example.jingdong.view.adapter.RightAdapter;
import com.example.jingdong.view.iview.RightView;
import com.example.jingdong.view.iview.ShouView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.uuzuche.lib_zxing.activity.CaptureActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ClassifyFragment extends Fragment implements ShouView, RightView {

    @BindView(R.id.rec_left)
    RecyclerView recLeft;
    @BindView(R.id.rec_right)
    RecyclerView recRight;
    Unbinder unbinder;
//    @BindView(R.id.simple_title)
//    SimpleDraweeView simpleTitle;
    @BindView(R.id.sao)
    ImageView sao;
    @BindView(R.id.xiaoxi)
    ImageView xiaoxi;
    @BindView(R.id.et_sou)
    EditText etSou;

    private ShouPresenter shouPresenter;
    private RightPresenter rightPresenter;

    //定义二维码扫描的变量
    private int REQUEST_CODE;
    private int cid;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_classify, container, false);
        unbinder = ButterKnife.bind(this, view);

        shouPresenter = new ShouPresenter(this);
        shouPresenter.shouye();

        rightPresenter = new RightPresenter(this);
        rightPresenter.rightData(1);

        initView();

        return view;
    }

    private void initView() {
        recRight.setLayoutManager(new LinearLayoutManager(getActivity()));
        recLeft.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void onSuccess(final ShouBean shouBean) {
        //二维码扫描
        sao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CaptureActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        List<ShouBean.DataBean.FenleiBean> fenlei = shouBean.getData().getFenlei();
        LeftAdapter leftAdapter = new LeftAdapter(getActivity(), fenlei);
        recLeft.setAdapter(leftAdapter);

        leftAdapter.setOnItemClickListener(new LeftAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                cid = shouBean.getData().getFenlei().get(position).getCid();
                rightPresenter.rightData(cid);
                //simpleTitle.setImageURI(Uri.parse(shouBean.getData().getFenlei().get(position).getIcon().split("\\|")[0]));
            }
        });
    }

    @Override
    public void onRight(RightBean rightBean) {
        List<RightBean.DataBean> data = rightBean.getData();
        RightAdapter rightAdapter = new RightAdapter(getActivity(), data);
        recRight.setAdapter(rightAdapter);

    }

    @Override
    public void onFailed(Throwable t) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.et_sou)
    public void onViewClicked() {
        Intent intent = new Intent(getActivity(),SouActivity.class);
        startActivity(intent);
    }
    
}
