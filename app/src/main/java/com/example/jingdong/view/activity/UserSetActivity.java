package com.example.jingdong.view.activity;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jingdong.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserSetActivity extends AppCompatActivity {

    @BindView(R.id.userset_txt)
    TextView usersetTxt;
    @BindView(R.id.userset_seticon)
    TextView usersetSeticon;
    @BindView(R.id.userset_icon)
    ImageView usersetIcon;
    @BindView(R.id.userset_setnick)
    TextView usersetSetnick;
    @BindView(R.id.userset_name)
    TextView usersetName;
    @BindView(R.id.userset_unlogin)
    Button usersetUnlogin;
    @BindView(R.id.huiview)
    View huiview;
    @BindView(R.id.userset_data)
    TextView usersetData;

    private static int CAMERA_REQUST_CODE = 1;
    private static int GALLERY_REQUST_CODE = 2;
    private static int CROP_REQUST_CODE = 3;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_set);
        ButterKnife.bind(this);

        SharedPreferences sp = getSharedPreferences("config", Activity.MODE_PRIVATE);
        String nickname = sp.getString("nickname", "");
        usersetName.setText(nickname);
    }

    @OnClick({R.id.userset_data, R.id.userset_unlogin,R.id.userset_icon})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.userset_data:
                new DatePickerDialog(UserSetActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int  year, int monthOfYear, int dayOfMonth) {
                        usersetData.setText(String.format("%d-%d-%d", year, monthOfYear + 1, dayOfMonth));
                    }
                }, 2000, 1, 2).show();
                break;
            case R.id.userset_unlogin:
                SharedPreferences sp = getSharedPreferences("config", Activity.MODE_PRIVATE);
                sp.edit().putString("login", "false").putInt("uid", 0).commit();
                finish();
                break;
            case R.id.userset_icon:
                AlertDialog.Builder builder = new AlertDialog.Builder(UserSetActivity.this);
                //创建视图
                final AlertDialog dialog = builder.create();
                View view1 = View.inflate(UserSetActivity.this, R.layout.icon_pop, null);

                dialog.setView(view1);
                TextView btn_pai = view1.findViewById(R.id.text_pai);
                TextView btn_bendi = view1.findViewById(R.id.text_xiang);
                btn_pai.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent,CAMERA_REQUST_CODE);
                    }
                });

                btn_bendi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                        intent.setType("image/*");
                        startActivityForResult(intent,GALLERY_REQUST_CODE);
                    }
                });

                dialog.show();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1&&resultCode==RESULT_OK){
            Bitmap bitmap = data.getParcelableExtra("data");
            usersetIcon.setImageBitmap(bitmap);
        }else if(requestCode==2&&resultCode==RESULT_OK){
            Uri uri = data.getData();
            usersetIcon.setImageURI(uri);

        }
    }
}
