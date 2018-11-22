package com.example.jingdong.utils;

import com.example.jingdong.bean.AddShopBean;
import com.example.jingdong.bean.HomexBean;
import com.example.jingdong.bean.LeftBean;
import com.example.jingdong.bean.LoginBean;
import com.example.jingdong.bean.RegisBean;
import com.example.jingdong.bean.RightBean;
import com.example.jingdong.bean.ShopBean;
import com.example.jingdong.bean.ShouBean;
import com.example.jingdong.bean.LieBean;
import com.example.jingdong.bean.UpdateIcon;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface Api {
    //首页接口
    @GET("home/getHome")
    Observable<ShouBean> shouye();

    //分类左边接口
    @GET("product/getCatagory")
    Observable<LeftBean> getleft();

    //分类右边接口
    @GET("product/getProductCatagory")
    Observable<RightBean> getright(@Query("cid") int cid);

    //搜索
    @GET("product/searchProducts")
    Observable<LieBean> lie(@Query("keywords") String keywords, @Query("page") int page);

    //首页跳详情
    @GET("product/getProductDetail")
    Observable<HomexBean> xiangqing(@Query("pid") int pid);

    //登录
    @GET("user/login")
    Observable<LoginBean> loginData(@Query("mobile") String mobile, @Query("password") String password);

    //注册
    @GET("user/reg")
    Observable<RegisBean> RegData(@Query("mobile") String mobile, @Query("password") String password);

    //上传头像
    @Multipart
    @POST("file/upload")
    Observable<UpdateIcon> upLoadIcon(@Query("uid") String uid, @Part MultipartBody.Part part);

    //购物车
    @GET("product/getCarts")
    Observable<ShopBean> gouwuche(@Query("uid") String uid);

    //添加购物车
    @GET("product/addCart")
    Observable<AddShopBean> getAdd(@Query("pid") String pid,@Query("uid") String uid);


}
