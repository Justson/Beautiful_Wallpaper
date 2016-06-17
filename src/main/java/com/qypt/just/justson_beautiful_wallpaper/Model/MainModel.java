package com.qypt.just.justson_beautiful_wallpaper.Model;

import android.util.Log;

import com.google.gson.Gson;
import com.qypt.just.justson_beautiful_wallpaper.Bean.QQInfoBean;
import com.qypt.just.justson_beautiful_wallpaper.Bean.ResultBean;
import com.qypt.just.justson_beautiful_wallpaper.Bean.SliderBean;
import com.qypt.just.justson_beautiful_wallpaper.Bean.SliderInfoBean;
import com.qypt.just.justson_beautiful_wallpaper.Bean.TopBean;
import com.qypt.just.justson_beautiful_wallpaper.Bean.WrapperBean;
import com.qypt.just.justson_beautiful_wallpaper.HttpUtils.HttpManager;
import com.qypt.just.justson_beautiful_wallpaper.HttpUtils.Retrofit.RetrofitUtils;
import com.qypt.just.justson_beautiful_wallpaper.HttpUtils.Retrofit.WallService;
import com.qypt.just.justson_beautiful_wallpaper.Presenter.MainPresenter;
import com.qypt.just.justson_beautiful_wallpaper.Utils.UrlUtils;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/6/1.
 */
public class MainModel implements IModel<TopBean> {
    private MainPresenter mainPresenter;
    private HttpManager httpManager;
    public MainModel(MainPresenter mainPresenter){
        this.mainPresenter=mainPresenter;
        httpManager=HttpManager.getInstance();
    }
    @Override
    public void getData(TopBean topBean,String url) {


        if(topBean instanceof SliderInfoBean){
            SliderBean sliderBean=new SliderBean();
            httpManager.onAsynPostGetListData(url, "WallPager", topBean, sliderBean, new HttpManager.OkHttpResult<Object>() {
                @Override
                public void onSucessfull(Object o) {
                    mainPresenter.TaskResult(new WrapperBean().setCurrentType(WrapperBean.WrapperBeanType.SLIDE).setObject(o)); //包装了一层类型
                }

                @Override
                public void onFail(Object o) {
                    mainPresenter.TaskResult(new WrapperBean().setCurrentType(WrapperBean.WrapperBeanType.SLIDE).setObject(o));
                }
            });
            return;
        }else if(topBean instanceof QQInfoBean){

            WallService wallService= RetrofitUtils.getRetrofitInstance(UrlUtils.LOGINBASEURL).create(WallService.class);
            Observable<ResultBean>observable=wallService.postData(new Gson().toJson(topBean));
            observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Subscriber<ResultBean>() {
                @Override
                public void onCompleted() {

                    Log.i("subscribeOn","onCompleted");
                }

                @Override
                public void onError(Throwable e) {
                    Log.i("subscribeOn","e ------------------- ");
                    mainPresenter.TaskResult(e);
                }

                @Override
                public void onNext(ResultBean resultBean) {
                    Log.i("subscribeOn","onNext");
                    mainPresenter.TaskResult(resultBean);

                }
            });

            return;
        }


        try {
            httpManager.onAsyncPostData(url, "WallPager", topBean, new HttpManager.OkHttpResult<Object>() {
                @Override
                public void onSucessfull(Object o) {
                        mainPresenter.TaskResult(new WrapperBean().setCurrentType(WrapperBean.WrapperBeanType.MAINFRAGMENTLIST).setObject(o));
                }

                @Override
                public void onFail(Object o) {
                    mainPresenter.TaskResult(new WrapperBean().setCurrentType(WrapperBean.WrapperBeanType.MAINFRAGMENTLIST).setObject(o));
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void processDta() {

    }
}
