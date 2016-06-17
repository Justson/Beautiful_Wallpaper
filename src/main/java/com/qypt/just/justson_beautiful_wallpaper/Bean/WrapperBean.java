package com.qypt.just.justson_beautiful_wallpaper.Bean;

/**
 * Created by Administrator on 2016/6/3.
 */
public class WrapperBean {

    public int currentType;
    public Object object;

    public int getCurrentType() {
        return currentType;
    }

    public WrapperBean setCurrentType(int currentType) {
        this.currentType = currentType;
        return this;
    }

    public Object getObject() {
        return object;
    }

    public WrapperBean setObject(Object object) {
        this.object = object;

        return this;
    }

    public  static class WrapperBeanType{

        public static final  int SLIDE=1;
        public static final int MAINFRAGMENTLIST=0;


    }

}
