package com.example.jingdong.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.jingdong.R;
import com.example.jingdong.bean.ShopBean;
import com.example.jingdong.view.widget.jiajian;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class ShopZiAdapter extends RecyclerView.Adapter<ShopZiAdapter.ViewHolder> {

    private Context context;
    private List<ShopBean.DataBean.ListBean> list;

    public ShopZiAdapter(Context context, List<ShopBean.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    public interface OnProductClickListener{
        void OnProductClickListener(int position, boolean ischecked);

    }

    private OnProductClickListener productClickListener;

    public void setOnProductClickListener(OnProductClickListener listener){
        this.productClickListener = listener;
    }

    public interface OnAddDecreaseProductListener{
        void OnChange(int position, int num);
    }

    private OnAddDecreaseProductListener productListener;

    public void setOnAddDecreaseProductListener(OnAddDecreaseProductListener listener){
        this.productListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.shopzi_adapter, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final ShopBean.DataBean.ListBean listBean = list.get(position);
        holder.imgproduct.setImageURI(listBean.getImages().split("\\|")[0]);
        holder.txtproductname.setText(listBean.getTitle());
        holder.txtsingleprice.setText(listBean.getPrice() + "");
        holder.advproduct.setNum(listBean.getNum());

        holder.advproduct.setOnAddDecreaseListener(new jiajian.OnAddDecreaseListener() {
            @Override
            public void adds(int num) {
                listBean.setNum(num);
                if (productListener != null){
                    productListener.OnChange(position,num);
                }
            }

            @Override
            public void decrease(int num) {
                listBean.setNum(num);
                if (productListener != null){
                    productListener.OnChange(position,num);
                }
            }
        });

        holder.cbproduct.setOnCheckedChangeListener(null);
        holder.cbproduct.setChecked(listBean.isChecked());
        holder.cbproduct.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                listBean.setChecked(isChecked);
                if (productClickListener != null){
                    productClickListener.OnProductClickListener(position,isChecked);
                }
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private final CheckBox cbproduct;
        private final SimpleDraweeView imgproduct;
        private final TextView txtproductname;
        private final TextView txtsingleprice;
        private final jiajian advproduct;
        private final ImageView delete;

        public ViewHolder(View itemView) {
            super(itemView);
            cbproduct = itemView.findViewById(R.id.cb_product);
            imgproduct = itemView.findViewById(R.id.img_product);
            txtproductname = itemView.findViewById(R.id.txt_product_name);
            txtsingleprice = itemView.findViewById(R.id.txt_single_price);
            advproduct = itemView.findViewById(R.id.adv_product);
            delete = itemView.findViewById(R.id.delete);
        }
    }
}
