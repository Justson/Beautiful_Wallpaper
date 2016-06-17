package com.qypt.just.justson_beautiful_wallpaper.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qypt.just.justson_beautiful_wallpaper.Activity.BaseActivity;
import com.qypt.just.justson_beautiful_wallpaper.Activity.IView;
import com.qypt.just.justson_beautiful_wallpaper.App.App;
import com.qypt.just.justson_beautiful_wallpaper.Bean.ImageBean;
import com.qypt.just.justson_beautiful_wallpaper.Bean.SQLiteBean;
import com.qypt.just.justson_beautiful_wallpaper.Presenter.MainPresenter;
import com.qypt.just.justson_beautiful_wallpaper.SQLiteDao.WallPaperDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/26.
 */
public abstract class BaseFragment extends Fragment implements IView {


    protected abstract int getLayoutResId();
    protected abstract MainPresenter getMainPresenter();
    protected BaseActivity baseActivity;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutResId(),container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        baseActivity= (BaseActivity) this.getActivity();
        if(getMainPresenter()!=null)
        getMainPresenter().attacthView(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(getMainPresenter()!=null)
        getMainPresenter().dispathView();
    }

    @Override
    public void onShowView() {

    }

    @Override
    public void onHideView() {

    }

    @Override
    public <T> void onComeData(T t) {

    }

    /**
     * 封装了更新数据库
     * @param list
     */
    protected void dataChangeUpdateSQLite(final List<ImageBean> list){

        if(list==null||list.isEmpty())
            return;
        new Thread(){
            @Override
            public void run() {
                List<SQLiteBean>sqllist=new ArrayList<SQLiteBean>();
                SQLiteBean sqLiteBean=null;
                for(ImageBean imageBean :list){
                    sqLiteBean=new SQLiteBean();
                    sqLiteBean.setUserName(App.userName);
                    sqLiteBean.setName(imageBean.getName());
                    sqLiteBean.setUrl(imageBean.getUrl());
                    sqLiteBean.setTime(System.currentTimeMillis()+"");
                    sqLiteBean.setPagerType("Main");
                    sqLiteBean.setType(imageBean.getType()==null?"":imageBean.getType().trim());
                    sqLiteBean.setFormat(imageBean.getFormat());
                    sqLiteBean.setWidth(imageBean.getWidth()+"");
                    sqLiteBean.setHeight(imageBean.getHeight()+"");
                    sqllist.add(sqLiteBean);
                }

                WallPaperDao wallPaperDao= WallPaperDao.getInstance();
                Log.i("Info","update sql");
                wallPaperDao.onInsertMultData(sqllist);

            }
        }.start();

    }

}
