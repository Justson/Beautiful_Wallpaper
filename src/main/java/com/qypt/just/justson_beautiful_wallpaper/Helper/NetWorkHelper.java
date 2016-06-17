package com.qypt.just.justson_beautiful_wallpaper.Helper;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.qypt.just.justson_beautiful_wallpaper.App.App;

/**
 * Created by Administrator on 2016/6/1.  判断网络是否可用
 */
public class NetWorkHelper {


    private boolean NetWorkIsOk(App app){

        Context context=app.getAppContext();

         ConnectivityManager manager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
         NetworkInfo[] networkInfos=manager.getAllNetworkInfo();
         for(NetworkInfo networkInfo:networkInfos){

            if( networkInfo.getState()==NetworkInfo.State.CONNECTED){
                return true;
            }
         }
        return false;
    }

}
