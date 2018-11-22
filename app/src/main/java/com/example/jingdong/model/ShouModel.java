package com.example.jingdong.model;

import com.example.jingdong.bean.ShouBean;
import com.example.jingdong.utils.Api;
import com.example.jingdong.utils.HttpUtils;

import io.reactivex.Observable;

public class ShouModel {
    public Observable<ShouBean> loadData(){
        Api api = HttpUtils.getInstance().create(Api.class);
        Observable<ShouBean> shouye = api.shouye();
        return shouye;
    }
}
