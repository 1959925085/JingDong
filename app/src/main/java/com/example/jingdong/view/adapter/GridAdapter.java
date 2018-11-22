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
import com.example.jingdong.bean.ShouBean;
import com.example.jingdong.view.activity.HomexActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class GridAdapter extends RecyclerView.Adapter<GridAdapter.ViewHolder>{

    private Context context;
    private List<ShouBean.DataBean.FenleiBean> list;

    public GridAdapter(Context context, List<ShouBean.DataBean.FenleiBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.grid_layout, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.simper_view.setImageURI(Uri.parse(list.get(position).getIcon().split("\\|")[0]));
        holder.text_grid.setText(list.get(position).getName());

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(context,HomexActivity.class);
//                ///intent.putExtra("pid",list.getList().get(position).getPid());
//                int pid = list.get(position).get
//                intent.putExtra("pid",pid);
//                context.startActivity(intent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private final SimpleDraweeView simper_view;
        private final TextView text_grid;

        public ViewHolder(View itemView) {
            super(itemView);

            simper_view = itemView.findViewById(R.id.simper_view);
            text_grid = itemView.findViewById(R.id.text_grid);
        }
    }

    public interface OnItemClickListener{
        void onItemClick(View view,int position);
    }

    private OnItemClickListener clickListener;

    public void setOnItemClickListener(OnItemClickListener clickListener){
        this.clickListener = clickListener;
    }
}
