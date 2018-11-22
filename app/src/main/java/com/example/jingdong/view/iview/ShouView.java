package com.example.jingdong.view.iview;

import com.example.jingdong.bean.ShouBean;

public interface ShouView {
    void onSuccess(ShouBean shouBean);

    void onFailed(Throwable t);
}
