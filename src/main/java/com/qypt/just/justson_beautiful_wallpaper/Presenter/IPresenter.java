package com.qypt.just.justson_beautiful_wallpaper.Presenter;

import com.qypt.just.justson_beautiful_wallpaper.Bean.TopBean;

/**
 * Created by Administrator on 2016/6/1.
 */
public  interface IPresenter {

    void dispatchTask(TopBean topBean,String url);
    <T> void TaskResult(T t);

}
