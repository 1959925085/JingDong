package com.example.jingdong.model;

import com.example.jingdong.bean.HomexBean;
import com.example.jingdong.utils.Api;
import com.example.jingdong.utils.HttpUtils;

import io.reactivex.Observable;

public class HomexModel {
    public Observable<HomexBean> xiangqing(int pid){
        Api api = HttpUtils.getInstance().create(Api.class);
        Observable<HomexBean> xiangqing = api.xiangqing(pid);
        return xiangqing;
    }
}
