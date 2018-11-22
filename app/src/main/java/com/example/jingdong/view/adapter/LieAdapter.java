package com.example.jingdong.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jingdong.R;
import com.example.jingdong.bean.LieBean;
import com.example.jingdong.view.activity.HomexActivity;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class LieAdapter extends RecyclerView.Adapter<LieAdapter.ViewHolder> {

    private Context context;
    private List<LieBean.DataBean> list;

    public LieAdapter(Context context, List<LieBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.lie_adapter, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.title.setText(list.get(position).getTitle());
        holder.price.setText(list.get(position).getPrice()+"");

        String images = list.get(position).getImages();
        String[] spilt = images.split("\\|");
        Uri parse = Uri.parse(spilt[0]);

        AbstractDraweeController freso = Fresco.newDraweeControllerBuilder()
                .setUri(parse)
                .build();
        holder.simple.setController(freso);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,HomexActivity.class);
                intent.putExtra("pid",list.get(position).getPid());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private final SimpleDraweeView simple;
        private final TextView title;
        private final TextView price;

        public ViewHolder(View itemView) {
            super(itemView);

            simple = itemView.findViewById(R.id.lie_simple);
            title = itemView.findViewById(R.id.lie_title);
            price = itemView.findViewById(R.id.lie_price);
        }
    }
}
