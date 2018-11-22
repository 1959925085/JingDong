package com.example.jingdong.model;

import com.example.jingdong.bean.LieBean;
import com.example.jingdong.utils.Api;
import com.example.jingdong.utils.HttpUtils;

import io.reactivex.Observable;

public class LieModel {
    public Observable<LieBean> lie(String keywords,int page){
        Api api = HttpUtils.getInstance().create(Api.class);
        Observable<LieBean> lie = api.lie(keywords, page);
        return lie;
    }
}
