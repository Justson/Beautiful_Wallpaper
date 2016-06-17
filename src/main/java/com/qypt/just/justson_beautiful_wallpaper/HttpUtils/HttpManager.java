package com.qypt.just.justson_beautiful_wallpaper.HttpUtils;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.google.gson.Gson;
import com.qypt.just.justson_beautiful_wallpaper.Bean.ImageBean;
import com.qypt.just.justson_beautiful_wallpaper.Bean.TopBean;
import com.qypt.just.justson_beautiful_wallpaper.Utils.JsonUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/6/1.   OkHttp二次封装
 */
public class HttpManager {

    private OkHttpClient okHttpClient;
    public static HttpManager httpManager;
    private Handler handler;
    private Gson mGson;
    private static int TYPE = 0;
    public static final int ENTITY = 1;
    public static final int LIST = 2;
    public static final int MAP = 2;

    public HttpManager() {
        /**
         * 配置OkHttp
         */
        okHttpClient = new OkHttpClient.Builder().readTimeout(6000L, TimeUnit.MILLISECONDS).connectTimeout(3000L, TimeUnit.MILLISECONDS).build();
        handler = new Handler(Looper.getMainLooper()); //异步处理后回传到主线程
        mGson = new Gson(); //json 解析
    }


    public <T> void onAsyncobtainData(String url, String name, TopBean topBean, final OkHttpResult<Object> mHttpResult, final T t, final int type) throws IOException {

        if (url == null || topBean == null || url.equals("") || topBean.toString().equals(""))
            return;

        Log.i("Info", "json:" + mGson.toJson(topBean));
        FormBody f = new FormBody.Builder().addEncoded(name, mGson.toJson(topBean)).build(); //构建请求体
        /**
         * 请求
         */
        Request request = new Request.Builder().url(url).post(f).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() { //加入调度
            @Override
            public void onFailure(Call call, IOException e) {
                onResultHandle(call, null, mHttpResult, t, type);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                onResultHandle(call, response, mHttpResult, t, type);
            }
        });
    }

    public <T> void onResultHandle(Call call, final Response response, final OkHttpResult okHttpResult, final T t, int type) {

        if (response == null || !response.isSuccessful()) { //连接失败

            handler.post(new Runnable() {
                @Override
                public void run() {
                    okHttpResult.onFail("连接失败");
                }
            });
        } else {
            /**
             * 在子线程解析json分轻主线程的工作
             */
            List<T> list = null;
            T tt=null;
            Map<String,T>map=null;
            try {
                String json = response.body().string();
                if (json != null && !json.equals("")) {
                    if(type==MAP){
                        map= (Map<String, T>) JsonUtils.parserJsonToMap(json,t.getClass());
                    }else if(type==LIST){
                            list = (List<T>) JsonUtils.parserJson(json);
                    }else if(type==ENTITY){
                        tt= (T) JsonUtils.parserJsonByEntity(json,t.getClass());
                    }


                    final Object object=type==MAP?map:type==LIST?list:tt;
                    handler.post(new Runnable() {
                        @Override
                        public void run() {

                            okHttpResult.onSucessfull(object);

                        }
                    });

                }
            } catch (Exception e) {
                e.printStackTrace();
            }


        }


    }


    public <T> void onAsynPostGetListData(String url, String name, TopBean topBean, T t, final OkHttpResult<Object> objectOkHttpResult) {
        List<T> list = new ArrayList<T>();
        list.add(t);
        this.onAsynPostCommon(url, name, topBean, objectOkHttpResult, null, list);
        t = null;
    }

    public <T> void onAsynPostGetEntity(String url, String name, TopBean topBean, final OkHttpResult<Object> objectOkHttpResult, T t) {
        this.onAsynPostCommon(url, name, topBean, objectOkHttpResult, t, null);
    }

    private <T, E> void onAsynPostCommon(String url, String name, TopBean topBean, final OkHttpResult<Object> objectOkHttpResult, final T result, final List<E> list) {

        if (url == null || topBean == null || url.equals("") || topBean.toString().equals(""))
            return;

        FormBody formBody = new FormBody.Builder().add(name, mGson.toJson(topBean)).build();
        final Request request = new Request.Builder().post(formBody).url(url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                onAsyncResult(null, objectOkHttpResult, result, list);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                onAsyncResult(response, objectOkHttpResult, result, list);
            }
        });

    }

    public <T, E> void onAsyncResult(Response response, final OkHttpResult okHttpResult, T t, List<E> list) {
        if (response == null || !response.isSuccessful()) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    okHttpResult.onFail("连接失败...");
                }
            });

        } else {

            try {
                String json = response.body().string();
                if (json == null) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            okHttpResult.onFail("暂无数据");
                        }
                    });

                    return;
                } else {
                    List<E> geList = null;
                    if (list != null) {

                        geList = (List<E>) JsonUtils.parserJsonByGeneric_List(json, list.get(0).getClass());
                        list.clear();
                        list = null;
                    } else {
                        t = (T) JsonUtils.parserJsonByEntity(json, t.getClass());
                    }

                    final List<E> fGeList = geList;
                    final T tt = t;

                    handler.post(new Runnable() {
                        @Override
                        public void run() {

                            if (tt == null && fGeList == null)
                                okHttpResult.onFail("解析数据失败");

                            okHttpResult.onSucessfull(fGeList == null ? tt : fGeList);
                        }
                    });

                }


            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    /**
     * post 方式异步访问网络
     *
     * @param url
     * @param name
     * @param topBean
     * @param mHttpResult
     * @param <T>
     * @throws IOException
     */
    public <T> void onAsyncPostData(String url, String name, TopBean topBean, final OkHttpResult<Object> mHttpResult) throws IOException {

        if (url == null || topBean == null || url.equals("") || topBean.toString().equals(""))
            return;

        Log.i("Info", "json:" + mGson.toJson(topBean));
        FormBody f = new FormBody.Builder().addEncoded(name, mGson.toJson(topBean)).build(); //构建请求体
        /**
         * 请求
         */
        Request request = new Request.Builder().url(url).post(f).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() { //加入调度
            @Override
            public void onFailure(Call call, IOException e) {
                onResult(call, null, mHttpResult);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                onResult(call, response, mHttpResult);
            }
        });
    }

    public void onResult(Call call, final Response response, final OkHttpResult okHttpResult) {

        if (response == null) { //连接失败

            handler.post(new Runnable() {
                @Override
                public void run() {
                    okHttpResult.onFail("连接失败");
                }
            });
        } else {

            /**
             * 在子线程解析json分轻主线程的工作
             */
            List<ImageBean> list = null;
            if (response.isSuccessful()) {
                try {
                    String json = response.body().string();
                    if (json != null && !json.equals("")) {
                        list = JsonUtils.parserJson(json);

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            final List<ImageBean> fList = list;
            handler.post(new Runnable() {
                @Override
                public void run() {

                    okHttpResult.onSucessfull(fList);

                }
            });
        }

    }


    public static HttpManager getInstance() {
        if (httpManager == null) {

            synchronized (HttpManager.class) {
                httpManager = new HttpManager();
            }
        }

        return httpManager;
    }


    public static interface OkHttpResult<T> {
        void onSucessfull(T t);

        void onFail(T t);
    }


}
