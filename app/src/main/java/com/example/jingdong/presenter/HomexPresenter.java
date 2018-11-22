package com.example.jingdong.presenter;

import android.util.Log;

import com.example.jingdong.bean.HomexBean;
import com.example.jingdong.model.HomexModel;
import com.example.jingdong.view.iview.HomexView;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomexPresenter {

    private HomexView homexView;
    private final HomexModel homexModel;

    public HomexPresenter(HomexView homexView) {
        this.homexView = homexView;
        homexModel = new HomexModel();
    }

    public void xiangqing(int pid){
        homexModel.xiangqing(pid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomexBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HomexBean homexBean) {
                        homexView.resultData(homexBean);
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
