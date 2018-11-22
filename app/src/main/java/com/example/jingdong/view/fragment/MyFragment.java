package com.example.jingdong.view.fragment;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.jingdong.R;
import com.example.jingdong.view.activity.LoginActivity;
import com.example.jingdong.view.activity.UserSetActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MyFragment extends Fragment {

    @BindView(R.id.img)
    SimpleDraweeView img;
    @BindView(R.id.deng)
    TextView deng;
    Unbinder unbinder;
    @BindView(R.id.my_head)
    RelativeLayout myHead;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.img, R.id.deng})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img:
                Intent intent1 = new Intent(getActivity(), UserSetActivity.class);
                startActivity(intent1);

                break;
            case R.id.deng:
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivityForResult(intent, 999);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 999 && resultCode == 1) {
            String mobile = data.getExtras().getString("mobile");
            deng.setText(mobile);
        }else {
            if (requestCode == 999 && resultCode == 888) {
                String name = data.getExtras().getString("name");
                String iconurl = data.getExtras().getString("iconurl");
                deng.setText(name);
                Uri parse = Uri.parse(iconurl);
                img.setImageURI(parse);
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences preferences = getActivity().getSharedPreferences("config",Activity.MODE_PRIVATE);
        //String username = preferences.getString("username","");
        String nickname = preferences.getString("nickname","");
        String login = preferences.getString("login", "");
        if (login.equals("true")){
            deng.setText(nickname);
        }else {
            deng.setText("登录/注册");
        }
    }
}
