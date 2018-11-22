package com.example.jingdong.view.iview;

import com.example.jingdong.bean.LoginBean;

public interface LoginView {

    void onLoginSuccess(LoginBean loginBean);
    void onError(LoginBean loginBean);
}
