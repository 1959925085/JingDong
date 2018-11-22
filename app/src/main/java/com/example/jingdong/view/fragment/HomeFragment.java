package com.example.jingdong.view.fragment;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.jingdong.R;
import com.example.jingdong.bean.ShouBean;
import com.example.jingdong.presenter.ShouPresenter;
import com.example.jingdong.view.activity.SouActivity;
import com.example.jingdong.view.adapter.GridAdapter;
import com.example.jingdong.view.adapter.MiaoAdapter;
import com.example.jingdong.view.adapter.TuiAdapter;
import com.example.jingdong.view.iview.ShouView;
import com.example.jingdong.view.widget.ObservScrollView;
import com.recker.flybanner.FlyBanner;
import com.sunfusheng.marqueeview.MarqueeView;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class HomeFragment extends Fragment implements ShouView {

    @BindView(R.id.sao)
    ImageView sao;
    @BindView(R.id.fly_banner)
    FlyBanner flyBanner;
    Unbinder unbinder;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.marqueeView)
    MarqueeView marqueeView;
    @BindView(R.id.recycler_miao)
    RecyclerView recyclerMiao;
    @BindView(R.id.recycler_tui)
    RecyclerView recyclerTui;
    @BindView(R.id.scrollview)
    ObservScrollView scrollView;
    @BindView(R.id.line)
    LinearLayout line;
    @BindView(R.id.xiaoxi)
    ImageView xiaoxi;
    @BindView(R.id.edit_sou)
    EditText editSou;

    //定义轮播集合
    private List<String> images = new ArrayList<>();

    private ShouPresenter shouPresenter;

    //定义二维码扫描的变量
    private int REQUEST_CODE;

    //搜索框
    private int imageHeight = 300;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, view);

        //创建p层
        shouPresenter = new ShouPresenter(this);
        //调用p层方法
        shouPresenter.shouye();

        //搜索框
        line.bringToFront();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onFailed(Throwable t) {

    }

    @Override
    public void onSuccess(ShouBean shouBean) {
        //二维码扫描
        sao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CaptureActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        //搜索框
        scrollView.setScrollViewListener(new ObservScrollView.ScrollViewListener() {
            @Override
            public void onScrollChanged(ObservScrollView scrollView, int x, int y, int oldx, int oldy) {
                if (y <= 0) {
                    line.setBackgroundColor(Color.argb((int) 0, 100, 29, 26));//AGB由相关工具获得，或者美工提供
                } else if (y > 0 && y <= imageHeight) {
                    float scale = (float) y / imageHeight;
                    float alpha = (255 * scale);
                    // 只是layout背景透明
                    line.setBackgroundColor(Color.argb((int) alpha, 227, 29, 26));
                } else {
                    line.setBackgroundColor(Color.argb((int) 255, 227, 29, 26));
                }
            }
        });

        //轮播集合
        List<ShouBean.DataBean.BannerBean> banner = shouBean.getData().getBanner();

        //循环轮播
        for (int i = 0; i < banner.size(); i++) {
            String replace = banner.get(i).getIcon().replace("https", "http");
            images.add(replace.split("\\|")[0]);
        }
        flyBanner.setImagesUrl(images);

        //九宫格
        List<ShouBean.DataBean.FenleiBean> fenlei = shouBean.getData().getFenlei();
        //设置布局管理器
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2, GridLayoutManager.HORIZONTAL, false));
        //设置适配器
        GridAdapter adapter = new GridAdapter(getActivity(), fenlei);
        recyclerView.setAdapter(adapter);

        //设置跑马灯
        List<String> info = new ArrayList<>();
        info.add("京东特惠");
        info.add("超级欢购日");
//        info.add("不曾相知亦无可思");
//        info.add("不曾相伴亦无可离");
//        info.add("不曾相恋亦无可分");
//        info.add("不曾相惜亦无可忆");
//        info.add("不曾相爱亦无可弃");
//        info.add("不曾相倦亦无可厌");
//        info.add("不曾相对亦无可随");
//        info.add("不曾相恨亦无可念");
        marqueeView.startWithList(info);

        //秒杀
        ShouBean.DataBean.MiaoshaBean miaosha = shouBean.getData().getMiaosha();
        //设置布局管理器
        recyclerMiao.setLayoutManager(new GridLayoutManager(getActivity(), 1, GridLayoutManager.HORIZONTAL, false));
        //设置适配器
        MiaoAdapter mAdapter = new MiaoAdapter(getActivity(), miaosha);
        recyclerMiao.setAdapter(mAdapter);

        //推荐
        ShouBean.DataBean.TuijianBean tuijian = shouBean.getData().getTuijian();
        //设置布局管理器
        recyclerTui.setLayoutManager(new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false));
        //设置适配器
        TuiAdapter tAdapter = new TuiAdapter(getActivity(), tuijian);
        recyclerTui.setAdapter(tAdapter);

    }

    //二维码方法
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == REQUEST_CODE) {
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    Toast.makeText(getActivity(), "解析结果:" + result, Toast.LENGTH_LONG).show();
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(getActivity(), "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    @OnClick(R.id.edit_sou)
    public void onViewClicked() {
        Intent intent = new Intent();
        intent.setClass(getActivity(),SouActivity.class);
        startActivity(intent);
        ((Activity) getActivity()).overridePendingTransition(R.anim.in_right, R.anim.out_left);

    }

}

