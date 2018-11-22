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
import com.example.jingdong.bean.RightBean;
import com.example.jingdong.view.activity.DataActivity;
import com.example.jingdong.view.activity.LieActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {

    private Context context;
    private List<RightBean.DataBean.ListBean> list;

    public ImageAdapter(Context context, List<RightBean.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.image_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.text_view.setText(list.get(position).getName());
        holder.simple_view.setImageURI(Uri.parse(list.get(position).getIcon().split("\\|")[0]));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = list.get(position).getName();
                //点击子条目跳转到列表页面
                Intent intent = new Intent(context, LieActivity.class);
                intent.putExtra("name",name);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final SimpleDraweeView simple_view;
        private final TextView text_view;

        public ViewHolder(View itemView) {
            super(itemView);

            simple_view = itemView.findViewById(R.id.simple_view);
            text_view = itemView.findViewById(R.id.text_view);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int layoutPosition = getLayoutPosition();
            onItemclickLinsten.onclick(layoutPosition);
        }
    }
    onItemclickLinsten onItemclickLinsten;

    public interface onItemclickLinsten{
        void onclick(int layoutPosition);
    }
    public void setonItemclickLinsten(onItemclickLinsten onItemclickLinsten){
        this.onItemclickLinsten= onItemclickLinsten;
    }

}
