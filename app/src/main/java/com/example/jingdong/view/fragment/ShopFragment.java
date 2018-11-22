package com.example.jingdong.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.jingdong.R;
import com.example.jingdong.bean.AddShopBean;
import com.example.jingdong.bean.ShopBean;
import com.example.jingdong.presenter.ShopPresenter;
import com.example.jingdong.view.adapter.ShopAdapter;
import com.example.jingdong.view.adapter.ShopZiAdapter;
import com.example.jingdong.view.iview.ShopView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ShopFragment extends Fragment implements ShopView {

    @BindView(R.id.cb_total_select)
    CheckBox cbTotalSelect;
    @BindView(R.id.txt_total_price)
    TextView txtTotalPrice;
    @BindView(R.id.ll_bottom)
    RelativeLayout llBottom;
    @BindView(R.id.rv_shopper)
    RecyclerView rvShopper;
    Unbinder unbinder;

    private String uid;
    private List<ShopBean.DataBean> list;
    private ShopAdapter shopAdapter;
    private ShopPresenter shopPresenter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop, container, false);
        unbinder = ButterKnife.bind(this, view);

        list = new ArrayList<>();
        setlistener();

        shopAdapter = new ShopAdapter(getActivity(), list);
        shopAdapter.setOnShopClickListener(new ShopAdapter.OnShopClickListener() {
            @Override
            public void OnShopClickListener(int position, boolean ischecked) {
                if (!ischecked) {
                    cbTotalSelect.setChecked(false);
                } else {
                    boolean isAddShopperChecked = true;
                    for (ShopBean.DataBean listshop : list) {
                        if (!listshop.isChecked()) {
                            isAddShopperChecked = false;
                            break;
                        }
                    }
                    cbTotalSelect.setChecked(isAddShopperChecked);
                }
                calulatePrice();
            }
        });

        shopAdapter.setOnAddDecreaseProductListener(new ShopZiAdapter.OnAddDecreaseProductListener() {
            @Override
            public void OnChange(int position, int num) {
                calulatePrice();
            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvShopper.setLayoutManager(layoutManager);
        rvShopper.setAdapter(shopAdapter);
        shopPresenter = new ShopPresenter(this);
        shopPresenter.gouwuche("20071");
        return view;
    }

    private void setlistener() {
        cbTotalSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isChecked = cbTotalSelect.isChecked();
                for (ShopBean.DataBean listshop : list) {
                    listshop.setChecked(isChecked);
                    List<ShopBean.DataBean.ListBean> product = listshop.getList();
                    for (ShopBean.DataBean.ListBean listBean : product) {
                        listBean.setChecked(isChecked);
                    }
                }
                calulatePrice();
                shopAdapter.notifyDataSetChanged();
            }
        });
    }

    private void calulatePrice() {
        float totalPrice = 0;
        for (ShopBean.DataBean listshop : list) {
            List<ShopBean.DataBean.ListBean> list = listshop.getList();
            for (ShopBean.DataBean.ListBean product : list) {
                if (product.isChecked()) {
                    totalPrice += product.getNum() * product.getPrice();
                }
            }
        }
        txtTotalPrice.setText("总价" + totalPrice);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onSuccesss(ShopBean shopBean) {
        list.clear();
        list.addAll(shopBean.getData());
        shopAdapter.notifyDataSetChanged();
    }

    @Override
    public void onAdd(AddShopBean addShopBean) {

    }

    @Override
    public void onErrorr(Throwable t) {

    }

    @Override
    public void onResume() {
        super.onResume();
        shopPresenter.gouwuche("20071");
    }
}
