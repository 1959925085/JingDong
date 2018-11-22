package com.example.jingdong.presenter;

import com.example.jingdong.bean.RightBean;
import com.example.jingdong.model.RightModel;
import com.example.jingdong.view.iview.RightView;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RightPresenter  {

    private RightView rightView;
    private final RightModel rightModel;

    public RightPresenter(RightView rightView) {
        this.rightView = rightView;
        rightModel = new RightModel();
    }

    public void rightData(int cid){
        rightModel.getright(cid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RightBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RightBean rightBean) {
                        rightView.onRight(rightBean);
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
