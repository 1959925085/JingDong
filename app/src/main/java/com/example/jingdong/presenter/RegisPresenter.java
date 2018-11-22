package com.example.jingdong.presenter;

import android.util.Log;

import com.example.jingdong.bean.LoginBean;
import com.example.jingdong.bean.RegisBean;
import com.example.jingdong.model.LoginModel;
import com.example.jingdong.model.RegisModel;
import com.example.jingdong.view.iview.LoginView;
import com.example.jingdong.view.iview.RegisView;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RegisPresenter {

    private RegisView regisView;
    private final RegisModel regisModel;

    public RegisPresenter(RegisView regisView) {
        this.regisView = regisView;
        regisModel = new RegisModel();
    }

    public void register(String mobile,String password){
        regisModel.regis(mobile, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegisBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RegisBean regisBean) {
                        //Log.e("vvvvv",lieBean.getData().get(0).getTitle()+"");
                        regisView.onRegSuccess(regisBean);
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
