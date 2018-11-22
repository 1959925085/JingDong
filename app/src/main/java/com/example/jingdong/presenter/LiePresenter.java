package com.example.jingdong.presenter;

import android.util.Log;

import com.example.jingdong.bean.HomexBean;
import com.example.jingdong.bean.LieBean;
import com.example.jingdong.model.HomexModel;
import com.example.jingdong.model.LieModel;
import com.example.jingdong.view.iview.HomexView;
import com.example.jingdong.view.iview.LieView;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LiePresenter {

    private LieView lieView;
    private final LieModel lieModel;

    public LiePresenter(LieView lieView) {
        this.lieView = lieView;
        lieModel = new LieModel();
    }

    public void getlie(String keywords,int page){
        lieModel.lie(keywords, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LieBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LieBean lieBean) {
                        Log.e("vvvvv",lieBean.getData().get(0).getTitle()+"");
                        lieView.onSuccess(lieBean);
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
