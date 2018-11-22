package com.example.jingdong.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.jingdong.R;

public class jiajian extends RelativeLayout implements View.OnClickListener {

    private int num = 1;
    private TextView txtadd;
    private TextView txtnum;
    private TextView txtdecrease;

    public int getNum(){
        return num;
    }

    public void setNum(int num){
        this.num = num;
        txtnum.setText(num + "");
    }

    public interface OnAddDecreaseListener{
        void adds(int num);
        void decrease(int num);
    }

    private OnAddDecreaseListener listener;

    public void setOnAddDecreaseListener(OnAddDecreaseListener listener){
        this.listener = listener;
    }

    public jiajian(Context context) {
        this(context,null);
    }

    public jiajian(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public jiajian(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View inflate = inflate(context, R.layout.jiajian, this);

        txtadd = findViewById(R.id.txt_add);
        txtnum = findViewById(R.id.txt_num);
        txtdecrease = findViewById(R.id.txt_decrease);

        txtnum.setText("1");
        txtadd.setOnClickListener(this);
        txtdecrease.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.txt_add:
                num++;
                txtnum.setText(num + "");
                if (listener != null){
                    listener.adds(num);
                }
                break;
            case R.id.txt_decrease:
                if (num > 1){
                    num--;
                }
                txtnum.setText(num + "");
                if (listener != null){
                    listener.decrease(num);
                }
                break;
        }
    }
}
