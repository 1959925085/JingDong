package com.example.jingdong.model;

import com.example.jingdong.bean.AddShopBean;
import com.example.jingdong.bean.ShopBean;
import com.example.jingdong.utils.Api;
import com.example.jingdong.utils.HttpUtils;

import io.reactivex.Observable;

public class ShopModel {
    public Observable<ShopBean> shopData(String uid){
        Api api = HttpUtils.getInstance().create(Api.class);
        Observable<ShopBean> shop = api.gouwuche(uid);
        return shop;
    }

    public Observable<AddShopBean> addShop(String pid,String uid){
        Api api = HttpUtils.getInstance().create(Api.class);
        Observable<AddShopBean> addshop = api.getAdd(pid,uid);
        return addshop;
    }
}
