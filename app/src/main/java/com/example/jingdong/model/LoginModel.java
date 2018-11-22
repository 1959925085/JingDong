package com.example.jingdong.model;

import com.example.jingdong.bean.LoginBean;
import com.example.jingdong.utils.Api;
import com.example.jingdong.utils.HttpUtils;

import io.reactivex.Observable;

public class LoginModel {
    public Observable<LoginBean> login(String mobile, String password){
        Api api = HttpUtils.getInstance().create(Api.class);
        Observable<LoginBean> login = api.loginData(mobile, password);
        return login;
    }
}
