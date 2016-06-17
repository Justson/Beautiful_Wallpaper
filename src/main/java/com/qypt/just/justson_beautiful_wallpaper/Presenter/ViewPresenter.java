package com.qypt.just.justson_beautiful_wallpaper.Presenter;

/**
 * Created by Administrator on 2016/6/1.
 */
public abstract class ViewPresenter<T> implements IPresenter {

      abstract  void attacthView(T t);
      abstract void dispathView();

}
