package com.qypt.just.justson_beautiful_wallpaper.Activity;

/**
 * Created by Administrator on 2016/6/1.
 */
public interface    IView {

    abstract  void onShowView();
    abstract <T>  void onComeData(T t);
    abstract  void onHideView();
}
