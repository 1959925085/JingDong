package com.example.jingdong.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

public class ObservScrollView extends ScrollView {

    public interface ScrollViewListener{
        void onScrollChanged(ObservScrollView scrollView,int x, int y,int oldx,int oldy);
    }

    private ScrollViewListener scrollViewListener = null;

    public ObservScrollView(Context context) {
        super(context);
    }

    public ObservScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ObservScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setScrollViewListener(ScrollViewListener scrollViewListener) {
        this.scrollViewListener = scrollViewListener;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldl);
        if (scrollViewListener != null) {
            scrollViewListener.onScrollChanged(this, l, t, oldl, oldl);
        }
    }
}
