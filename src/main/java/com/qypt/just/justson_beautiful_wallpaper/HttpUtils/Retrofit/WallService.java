package com.qypt.just.justson_beautiful_wallpaper.HttpUtils.Retrofit;

import com.qypt.just.justson_beautiful_wallpaper.Bean.ResultBean;

import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2016/6/16.
 */
public interface WallService {

    @FormUrlEncoded
    @POST("LoginServlet")
    Observable<ResultBean> postData(@Field("QQLogin") String str);

}
