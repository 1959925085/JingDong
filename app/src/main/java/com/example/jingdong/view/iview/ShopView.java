package com.example.jingdong.view.iview;

import com.example.jingdong.bean.AddShopBean;
import com.example.jingdong.bean.ShopBean;

public interface ShopView {

    void onSuccesss(ShopBean shopBean);

    void onAdd(AddShopBean addShopBean);

    void onErrorr(Throwable t);
}
