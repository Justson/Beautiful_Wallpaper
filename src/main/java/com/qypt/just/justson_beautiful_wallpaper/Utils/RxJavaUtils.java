package com.qypt.just.justson_beautiful_wallpaper.Utils;


import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Created by Administrator on 2016/6/15.
 */
public class RxJavaUtils {

    private final static Subject<Object,Object> observable = new SerializedSubject<>(PublishSubject.create());

    private  static RxJavaUtils rxJavaUtils;

    public void onPost(Object object){
        observable.onNext(object);
    }

    public  static RxJavaUtils getInstance(){

        if(rxJavaUtils==null){

            synchronized (RxJavaUtils.class){
                rxJavaUtils=new RxJavaUtils();
            }

        }
        return rxJavaUtils;
    }

    public Subject getObservable(){
        return this.observable;
    }

    //判断是否有观察者
    public boolean hasObserver(){
        return observable.hasObservers();
    }

}
