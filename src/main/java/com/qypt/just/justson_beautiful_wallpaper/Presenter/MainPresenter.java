package com.qypt.just.justson_beautiful_wallpaper.Presenter;

import com.qypt.just.justson_beautiful_wallpaper.Activity.IView;
import com.qypt.just.justson_beautiful_wallpaper.Bean.TopBean;
import com.qypt.just.justson_beautiful_wallpaper.Model.MainModel;

/**
 * Created by Administrator on 2016/6/1.
 */
public class MainPresenter extends ViewPresenter<IView> {
    private IView iView;
    private MainModel mainModel;

    public MainPresenter (){
        mainModel=new MainModel(this);
    }
    @Override
  public  void attacthView(IView iView) {

        this.iView=iView;
    }

    @Override
   public void dispathView() {

        this.iView=null;
}

    @Override //分发任务
    public void dispatchTask( TopBean topBean,String url) {

        if(iView!=null)
            iView.onShowView();
        if(mainModel!=null)
            mainModel.getData(topBean, url);

    }

    @Override  //任务处理结果
    public <T> void TaskResult(T t) {
        if(iView==null)
            return;
         iView.onComeData(t);
         iView.onHideView();
    }
}
