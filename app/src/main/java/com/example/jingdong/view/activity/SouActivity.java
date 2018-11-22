package com.example.jingdong.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.jingdong.R;
import com.example.jingdong.view.adapter.SouSuoAdapter;
import com.example.jingdong.view.widget.Sousuo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SouActivity extends AppCompatActivity {

    @BindView(R.id.et)
    EditText et;
    @BindView(R.id.lv)
    ListView lv;
    @BindView(R.id.sousou)
    Sousuo sousou;
    @BindView(R.id.but)
    Button but;

    private List<String> list = new ArrayList<>();
    private SouSuoAdapter souSuoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sou);
        ButterKnife.bind(this);
    }

    public void delete(View view) {
        list.clear();
        souSuoAdapter.notifyDataSetChanged();
    }

    @OnClick(R.id.but)
    public void onViewClicked() {
        String s = et.getText().toString();

        souSuoAdapter = new SouSuoAdapter(list, this);
        lv.setAdapter(souSuoAdapter);
        list.add(s);
        souSuoAdapter.notifyDataSetChanged();

        String string = et.getText().toString();
        Intent intent = new Intent(SouActivity.this,LieActivity.class);
        intent.putExtra("name",string);
        et.setText("");
        startActivity(intent);
        overridePendingTransition(R.anim.fade_out,R.anim.fade_in);

    }
}
