package com.example.jingdong.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jingdong.R;
import com.example.jingdong.bean.RightBean;
import com.example.jingdong.view.activity.DataActivity;

import java.util.List;

public class RightAdapter extends RecyclerView.Adapter<RightAdapter.ViewHolder> {

    private Context context;
    private List<RightBean.DataBean> listBeans;

    public RightAdapter(Context context, List<RightBean.DataBean> listBeans) {
        this.context = context;
        this.listBeans = listBeans;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.right_layout, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.title.setText(listBeans.get(position).getName());

        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false);
        holder.rightrev.setLayoutManager(gridLayoutManager);

        ImageAdapter imageAdapter = new ImageAdapter(context, listBeans.get(position).getList());
        holder.rightrev.setAdapter(imageAdapter);
    }

    @Override
    public int getItemCount() {
        return listBeans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView title;
        private final RecyclerView rightrev;

        public ViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.right_title);
            rightrev = itemView.findViewById(R.id.right_rev);
        }
    }
}
