package com.example.jingdong.view.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jingdong.R;
import com.example.jingdong.bean.LeftBean;
import com.example.jingdong.bean.RightBean;
import com.example.jingdong.bean.ShouBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class LeftAdapter extends RecyclerView.Adapter<LeftAdapter.ViewHolder> {

    private Context context;
    private List<ShouBean.DataBean.FenleiBean> list;

    public LeftAdapter(Context context, List<ShouBean.DataBean.FenleiBean> list) {
        this.context = context;
        this.list = list;
    }

    public interface OnItemClickListener{
        void onItemClick(View view,int position);
    }

    private OnItemClickListener clickListener;
    public void setOnItemClickListener(OnItemClickListener clickListener){
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.left_layout, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.text_left.setText(list.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickListener != null){
                    clickListener.onItemClick(view,position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView text_left;

        public ViewHolder(View itemView) {
            super(itemView);

            text_left = itemView.findViewById(R.id.text_left);
        }
    }
}
