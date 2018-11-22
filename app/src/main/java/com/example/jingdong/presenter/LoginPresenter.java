package com.example.jingdong.presenter;

import android.util.Log;

import com.example.jingdong.bean.LieBean;
import com.example.jingdong.bean.LoginBean;
import com.example.jingdong.model.LieModel;
import com.example.jingdong.model.LoginModel;
import com.example.jingdong.view.iview.LieView;
import com.example.jingdong.view.iview.LoginView;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginPresenter {

    private LoginView loginView;
    private final LoginModel loginModel;

    public LoginPresenter(LoginView loginView) {
        this.loginView = loginView;
        loginModel = new LoginModel();
    }

    public void login(String mobile,String password){
        loginModel.login(mobile, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                        //Log.e("vvvvv",lieBean.getData().get(0).getTitle()+"");
                        loginView.onLoginSuccess(loginBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("ggggg",e+"");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
