package com.example.jingdong.utils;

import java.util.concurrent.TimeUnit;
import java.util.zip.CRC32;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpUtils {
    private static final String BASE_URL = "http://www.zhaoapi.cn/";
    private Retrofit retrofit;

    private static final class SINGLE_INSTANCE{
        private static final HttpUtils _INSTANCE = new HttpUtils();
    }

    public static HttpUtils getInstance(){
        return SINGLE_INSTANCE._INSTANCE;
    }

    private HttpUtils(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(buildOkHttpClient())
                .build();
    }

    private OkHttpClient buildOkHttpClient() {
        return new OkHttpClient.Builder()
                .readTimeout(5000,TimeUnit.MILLISECONDS)
                .writeTimeout(5000,TimeUnit.MILLISECONDS)
                .build();
    }

    public <T> T create(Class<T> clazz){
        return retrofit.create(clazz);
    }
}
