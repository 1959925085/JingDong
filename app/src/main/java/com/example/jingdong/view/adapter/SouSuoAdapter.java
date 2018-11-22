package com.example.jingdong.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.jingdong.R;

import java.util.List;

public class SouSuoAdapter extends BaseAdapter {
    private List<String> list;
    private Context context;

    public SouSuoAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
       ViewHolder holder;
       if (view == null){
           holder = new ViewHolder();
           view = View.inflate(context, R.layout.sou_adapter,null);
           holder.tv = view.findViewById(R.id.tv);
           view.setTag(holder);
       }else {
           holder = (ViewHolder) view.getTag();
       }
       holder.tv.setText(list.get(i).toString());
       return view;
    }

    public class ViewHolder{
        TextView tv;
    }
}
