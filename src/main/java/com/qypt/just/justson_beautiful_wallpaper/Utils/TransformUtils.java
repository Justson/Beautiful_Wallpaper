package com.qypt.just.justson_beautiful_wallpaper.Utils;

import com.qypt.just.justson_beautiful_wallpaper.Bean.QQInfoBean;

import java.util.Map;

/**
 * Created by Administrator on 2016/6/16.
 */
public class TransformUtils {


    public static QQInfoBean storeDataInService(Map<String, String> map) {
        //转化成Bean储存
        QQInfoBean qqInfoBean=new QQInfoBean();
        qqInfoBean.setGender(map.get("gender"));
        qqInfoBean.setMsg(map.get("msg"));
        qqInfoBean.setOpenid(map.get("openid"));
        qqInfoBean.setProfile_image_url(map.get("profile_image_url"));
        qqInfoBean.setScreen_name(map.get("screen_name"));
        qqInfoBean.setCity(map.get("city"));

        return qqInfoBean;
    }

    public static QQInfoBean storeDataInServicePoint(Map<String, String> map,QQInfoBean qqInfoBean) {
        //转化成Bean储存
        if(qqInfoBean==null)
            return null;
        qqInfoBean.setGender(map.get("gender"));
        qqInfoBean.setMsg(map.get("msg"));
        qqInfoBean.setOpenid(map.get("openid"));
        qqInfoBean.setProfile_image_url(map.get("profile_image_url"));
        qqInfoBean.setScreen_name(map.get("screen_name"));
        qqInfoBean.setCity(map.get("city"));

        return qqInfoBean;
    }
}
