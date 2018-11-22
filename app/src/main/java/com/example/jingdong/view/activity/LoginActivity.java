package com.example.jingdong.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jingdong.R;
import com.example.jingdong.bean.LoginBean;
import com.example.jingdong.presenter.LoginPresenter;
import com.example.jingdong.view.iview.LoginView;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements LoginView {


    @BindView(R.id.edit_name)
    EditText editName;
    @BindView(R.id.edit_pass)
    EditText editPass;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.btn_regis)
    TextView btnRegis;
    @BindView(R.id.imgview_qq)
    ImageView imgviewQq;

    private LoginPresenter loginPresenter;
    private SharedPreferences sp;
    private UMShareAPI umShareAPI;
    private String name1;
    private String iconurl;
    private String name;
    private String pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        umShareAPI = UMShareAPI.get(this);
        loginPresenter = new LoginPresenter(this);
    }

    @Override
    public void onLoginSuccess(LoginBean loginBean) {
        Toast.makeText(this, loginBean.getMsg(), Toast.LENGTH_SHORT).show();
        String code = loginBean.getCode();
        String msg = loginBean.getMsg();
        if (code.equals("0")) {
           String mobile = loginBean.getData().getMobile();
           String uid = String.valueOf(loginBean.getData().getUid());
           String token = loginBean.getData().getToken();
            sp = getSharedPreferences("config",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("mobile",mobile);
            editor.putString("uid",uid);
            editor.putString("token", token);

            editor.putString("name",name);
            editor.putString("pwd",pass);

            editor.putInt("islogin",1);
            editor.commit();

            sp.edit().putString("username",loginBean.getData().getUsername()).putString("login","true")
                    .putInt("uid",loginBean.getData().getUid()).putString("nickname",loginBean.getData().getNickname()).commit();

            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putString("mobile", mobile);
            intent.putExtras(bundle);
            setResult(1, intent);
            finish();
        }
    }

    @Override
    public void onError(LoginBean loginBean) {

    }

    @OnClick({R.id.btn_login, R.id.btn_regis, R.id.imgview_qq})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                name = editName.getText().toString();
                pass = editPass.getText().toString();
                loginPresenter.login(name, pass);
                break;
            case R.id.btn_regis:
                Intent intent1 = new Intent(LoginActivity.this, RegisActivity.class);
                startActivity(intent1);
                finish();
                break;
            case R.id.imgview_qq:
                UMAuthListener authListener = new UMAuthListener() {
                    /**
                     * @desc 授权开始的回调
                     * @param platform 平台名称
                     */
                    @Override
                    public void onStart(SHARE_MEDIA platform) {

                    }

                    /**
                     * @desc 授权成功的回调
                     * @param platform 平台名称
                     * @param action 行为序号，开发者用不上
                     * @param data 用户资料返回
                     */
                    @Override
                    public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {

                        Toast.makeText(LoginActivity.this, "QQ登录成功", Toast.LENGTH_LONG).show();

                        name1 = data.get("name");
                        iconurl = data.get("iconurl");
                        Intent intent2 = new Intent();
                        Bundle bundle = new Bundle();
                        bundle.putString("name", name1);
                        bundle.putString("iconurl", iconurl);
                        intent2.putExtras(bundle);
                        setResult(888, intent2);
                            finish();
                        }

                    /**
                     * @desc 授权失败的回调
                     * @param platform 平台名称
                     * @param action 行为序号，开发者用不上
                     * @param t 错误原因
                     */
                    @Override
                    public void onError(SHARE_MEDIA platform, int action, Throwable t) {

                        Toast.makeText(LoginActivity.this, "失败：" + t.getMessage(),Toast.LENGTH_LONG).show();
                    }

                    /**
                     * @desc 授权取消的回调
                     * @param platform 平台名称
                     * @param action 行为序号，开发者用不上
                     */
                    @Override
                    public void onCancel(SHARE_MEDIA platform, int action) {
                        Toast.makeText(LoginActivity.this, "取消了", Toast.LENGTH_LONG).show();
                    }
                };
                umShareAPI.getPlatformInfo(LoginActivity.this, SHARE_MEDIA.QQ, authListener);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
}
