package com.example.jingdong.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.jingdong.R;
import com.example.jingdong.bean.ShopBean;

import java.util.List;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ViewHolder> {

    private Context context;
    private List<ShopBean.DataBean> list;

    public ShopAdapter(Context context, List<ShopBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    public interface OnShopClickListener{
        void OnShopClickListener(int position, boolean ischecked);

    }

    private OnShopClickListener shopClickListener;

    public void setOnShopClickListener(OnShopClickListener listener){
        this.shopClickListener = listener;
    }

    private ShopZiAdapter.OnAddDecreaseProductListener productListener;

    public void setOnAddDecreaseProductListener(ShopZiAdapter.OnAddDecreaseProductListener listener){
        this.productListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.shop_adapter, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final ShopBean.DataBean dataBean = list.get(position);
        holder.txtshopname.setText(list.get(position).getSellerName());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        holder.rvproduct.setLayoutManager(layoutManager);
        final ShopZiAdapter shopZiAdapter = new ShopZiAdapter(context, dataBean.getList());
        if (productListener != null){
            shopZiAdapter.setOnAddDecreaseProductListener(productListener);
        }

        shopZiAdapter.setOnProductClickListener(new ShopZiAdapter.OnProductClickListener() {
            @Override
            public void OnProductClickListener(int position, boolean ischecked) {
                if (!ischecked){
                    dataBean.setChecked(false);
                    shopClickListener.OnShopClickListener(position,false);
                }else {
                    boolean isAllProductSelected = true;
                    for (ShopBean.DataBean.ListBean listBean : dataBean.getList()){
                        if (!listBean.isChecked()){
                            isAllProductSelected = false;
                            break;
                        }
                    }
                    dataBean.setChecked(isAllProductSelected);
                    shopClickListener.OnShopClickListener(position,true);
                }
                notifyDataSetChanged();
                productListener.OnChange(0,0);
            }
        });
        holder.rvproduct.setAdapter(shopZiAdapter);
        holder.cbshopper.setOnCheckedChangeListener(null);
        holder.cbshopper.setChecked(dataBean.isChecked());
        holder.cbshopper.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                List<ShopBean.DataBean.ListBean> productlist = dataBean.getList();
                for (ShopBean.DataBean.ListBean producta : productlist){
                    producta.setChecked(isChecked);
                }
                shopZiAdapter.notifyDataSetChanged();
                if (shopClickListener != null){
                    shopClickListener.OnShopClickListener(position,isChecked);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private final CheckBox cbshopper;
        private final TextView txtshopname;
        private final RecyclerView rvproduct;

        public ViewHolder(View itemView) {
            super(itemView);

            cbshopper = itemView.findViewById(R.id.cb_shop);
            txtshopname = itemView.findViewById(R.id.txt_shop_name);
            rvproduct = itemView.findViewById(R.id.rv_product);
        }
    }
}
