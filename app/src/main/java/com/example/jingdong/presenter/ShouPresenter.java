package com.example.jingdong.presenter;

import com.example.jingdong.bean.ShouBean;
import com.example.jingdong.model.ShouModel;
import com.example.jingdong.view.iview.ShouView;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ShouPresenter{

    private ShouView shouView;
    private final ShouModel shouModel;

    public ShouPresenter(ShouView shouView) {
        this.shouView = shouView;
        shouModel = new ShouModel();
    }

    public void shouye(){
        shouModel.loadData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ShouBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ShouBean shouBean) {
                        shouView.onSuccess(shouBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
