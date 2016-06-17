package com.qypt.just.justson_beautiful_wallpaper.App;

import android.app.Application;
import android.content.Context;

import com.umeng.socialize.PlatformConfig;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/5/26.
 */
public class App extends Application {

    private static App context;
    public Map<String,Object> map=new HashMap<String,Object>();
    public static String userName;
    @Override
    public void onCreate() {
        super.onCreate();

        context=(App)this.getApplicationContext();
        PlatformConfig.setQQZone("1105394413", "eAmg40j7heIOGT5A");
    }

    //获取全局App的context
    public  static Context getAppContext(){
        return context;
    }

}
