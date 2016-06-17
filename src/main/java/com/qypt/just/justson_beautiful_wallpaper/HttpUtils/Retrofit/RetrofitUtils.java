package com.qypt.just.justson_beautiful_wallpaper.HttpUtils.Retrofit;

import com.squareup.okhttp.OkHttpClient;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * Created by Administrator on 2016/6/15.
 */
public class RetrofitUtils {


    public static Retrofit getRetrofitInstance(String baseUrl) {
        retrofit.Retrofit retrofit=new retrofit.Retrofit.Builder().baseUrl(baseUrl).client(new OkHttpClient())
               .addConverterFactory(GsonConverterFactory.create()) .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
        return retrofit;
    }


}
