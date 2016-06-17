package com.qypt.just.justson_beautiful_wallpaper.Utils;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.qypt.just.justson_beautiful_wallpaper.Activity.IView;
import com.qypt.just.justson_beautiful_wallpaper.App.App;
import com.qypt.just.justson_beautiful_wallpaper.Bean.QQInfoBean;
import com.qypt.just.justson_beautiful_wallpaper.Bean.QQLoginBean;
import com.qypt.just.justson_beautiful_wallpaper.Bean.ResultBean;
import com.qypt.just.justson_beautiful_wallpaper.Presenter.MainPresenter;
import com.qypt.just.justson_beautiful_wallpaper.R;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2016/6/15.
 */
public class OauthLoagin implements UMAuthListener, IView {

    public UMShareAPI umShareAPI;
    private Activity activity;
    private DialogManager dialogManager;
    private MainPresenter mainPresenter;
    public static final int LOGIN_CUCESS=1;
    public static final int LOGIN_FAIL=1;
    private Map<String,String>map;

    public OauthLoagin(Activity activity) {
        umShareAPI = UMShareAPI.get(App.getAppContext());
        this.activity = activity;
    }

    public void onOauthLogin(SHARE_MEDIA platform) {

        umShareAPI.doOauthVerify(activity, platform, this);
        dialogManager = new DialogManager();
        dialogManager.initDialog(R.layout.dialog_login, activity);


        mainPresenter = new MainPresenter();
        mainPresenter.attacthView(this);
    }


    @Override   //获取用户UID成功返回
    public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {

        String uid = map.get("uid");
        dialogManager.onShow();
        if (uid != null && !uid.equals("")) {
            umShareAPI.getPlatformInfo(activity, share_media, new UMAuthListener() {   //通过UID去获取用户的资料
                @Override  //成功获取信息
                public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                    StringBuffer sb = new StringBuffer();
                    Set<String> set = map.keySet();
                    for (String key : set) {
                        sb.append(key + ":" + map.get(key));
                    }
                    Toast.makeText(App.getAppContext(), sb.toString(), Toast.LENGTH_SHORT).show();
                    Log.i("Info", "sb:" + sb);

                    OauthLoagin.this.map=map;

                    QQInfoBean qqInfoBean = TransformUtils.storeDataInService(map);
                    mainPresenter.dispatchTask(qqInfoBean, "");
                }

                @Override
                public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
                    Toast.makeText(App.getAppContext(), "onError", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCancel(SHARE_MEDIA share_media, int i) {
                    Toast.makeText(App.getAppContext(), "cancel", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
        Toast.makeText(App.getAppContext(), "onError", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCancel(SHARE_MEDIA share_media, int i) {
        Toast.makeText(App.getAppContext(), "cancel", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onShowView() {

    }

    @Override
    public <T> void onComeData(T t) {

        if(t instanceof ResultBean){
            ResultBean resultBean= (ResultBean) t;

            if(resultBean.getCode()==LOGIN_CUCESS){

                QQLoginBean qqLoginBean=new QQLoginBean();
                qqLoginBean.setCode(resultBean.getCode());
                qqLoginBean.setContent(resultBean.getContent());
                qqLoginBean= (QQLoginBean) TransformUtils.storeDataInServicePoint(OauthLoagin.this.map,qqLoginBean);
                map.clear();

                if (RxJavaUtils.getInstance().hasObserver()) {
                    activity.finish();
                    activity = null;
                    RxJavaUtils.getInstance().onPost(qqLoginBean);
                    mainPresenter.dispathView();
                }
            }

        }


    }

    @Override
    public void onHideView() {
        dialogManager.onDismiss();
    }
}
