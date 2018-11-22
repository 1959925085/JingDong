package com.example.jingdong.presenter;

import com.example.jingdong.bean.AddShopBean;
import com.example.jingdong.bean.ShopBean;
import com.example.jingdong.model.ShopModel;
import com.example.jingdong.view.iview.ShopView;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ShopPresenter {

    public ShopView shopView;
    private final ShopModel shopModel;


    public ShopPresenter(ShopView shopView) {
        this.shopView = shopView;
        this.shopModel = new ShopModel();
    }

    public void gouwuche(String uid){
        shopModel.shopData(uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ShopBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ShopBean shopBean) {
                        shopView.onSuccesss(shopBean);

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void addshop(String pid,String uid){
        shopModel.addShop(pid,uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AddShopBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(AddShopBean addShopBean) {
                        shopView.onAdd(addShopBean);
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
