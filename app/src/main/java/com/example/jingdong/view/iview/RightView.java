package com.example.jingdong.view.iview;

import com.example.jingdong.bean.RightBean;

public interface RightView {
    void onRight(RightBean rightBean);

    void onFailed(Throwable t);
}
