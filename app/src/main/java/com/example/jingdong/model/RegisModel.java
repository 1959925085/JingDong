package com.example.jingdong.model;

import com.example.jingdong.bean.RegisBean;
import com.example.jingdong.utils.Api;
import com.example.jingdong.utils.HttpUtils;

import io.reactivex.Observable;

public class RegisModel {
    public Observable<RegisBean> regis(String mobile, String password){
        Api api = HttpUtils.getInstance().create(Api.class);
        Observable<RegisBean> regis = api.RegData(mobile, password);
        return regis;
    }
}
