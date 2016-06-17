package com.qypt.just.justson_beautiful_wallpaper.Utils;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.qypt.just.justson_beautiful_wallpaper.Bean.ImageBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/6/1.
 */
public class JsonUtils {


        public static List<ImageBean> parserJson(String json){
            List<ImageBean>list=null;
            Gson mGson=null;
            try{
                mGson=new Gson();
                list=mGson.fromJson(json,new TypeToken<List<ImageBean>>(){}.getType());

            }catch (Exception e){

            }finally {
                mGson=null;
            }

            Log.i("Info","JsonParser:"+list);
         return list;
        }

    public static <T> Map<String,T> parserJsonToMap(String json,Class<T>cla){

        Map<String, T> map = null;
        Gson gson=new Gson();
        if (gson != null) {
            map = gson.fromJson(json, new TypeToken<Map<String, T>>() {
            }.getType());
        }
        return map;
    }

    public static <T> List<T> parserJsonByGeneric_List(String json ,Class<T>cla){
        List<T>list=null;

        list=new ArrayList<T>();
        Gson mGon=new Gson();
        JsonArray array=new JsonParser().parse(json).getAsJsonArray();
        for(final JsonElement element:array){
            list.add(mGon.fromJson(element,cla));
        }

        Thread a;
//        if(json==null||json.equals("")||cla==null)
//            return null;
//        Gson mGson=null;
//        try {
//            mGson=new Gson();
//            list=mGson.fromJson(json,new TypeToken<List<T>>(){}.getType());
//        }catch (Exception e){
//            e.printStackTrace();
//        }finally {
//            mGson=null;
//        }

        return list;

    }

    public static <T>  T parserJsonByEntity(String json,Class<T>cla){

        T t=null;
        Gson mGson=null;
        try {
            mGson=new Gson();
           t= mGson.fromJson(json,cla);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            mGson=null;
        }

        return t;

    }




}
