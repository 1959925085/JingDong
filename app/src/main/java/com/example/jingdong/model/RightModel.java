package com.example.jingdong.model;

import com.example.jingdong.bean.RightBean;
import com.example.jingdong.utils.Api;
import com.example.jingdong.utils.HttpUtils;

import io.reactivex.Observable;

public class RightModel {
    public Observable<RightBean> getright(int cid){
        Api api = HttpUtils.getInstance().create(Api.class);
        Observable<RightBean> getright = api.getright(cid);
        return getright;
    }
}
