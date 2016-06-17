package com.qypt.just.justson_beautiful_wallpaper.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.qypt.just.justson_beautiful_wallpaper.App.App;
import com.qypt.just.justson_beautiful_wallpaper.Fragment.BaseFragment;

/**
 * Created by Administrator on 2016/5/26.
 */
public abstract class BaseActivity extends AppCompatActivity {

    private Intent intent;
    private FragmentManager fg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getContentViewResId());
        initView();
        init(savedInstanceState);
        fg = this.getSupportFragmentManager();
    }

    protected abstract void init(@Nullable Bundle savedInstanceState);

    protected abstract void initView();

    protected abstract int getContentLayout();

    protected abstract int getContentViewResId();
    //显示Toast
    protected void showToast(final String msg){

        this.runOnUiThread(new Runnable() {  //运行在UI线程
            @Override
            public void run() {
                Toast.makeText(App.getAppContext(), msg, Toast.LENGTH_SHORT).show();
            }
        });

    }

    protected  void turnToActivity(Class<? extends Activity> cla,Bundle bundle,int requestCode){

        Intent intent=getIntent();
        intent.setClass(this,cla);
        if(bundle!=null)
        intent.putExtras(bundle);

        this.startActivityForResult(intent,requestCode);
    }

    public  synchronized  Intent getIntent(){

        if(intent==null){
            intent=new Intent();
        }
        return intent;

    }


    public  void startFragment(int contentResId,BaseFragment baseFragment,String stack,boolean tag){

       if(tag==false){
           fg.beginTransaction().replace(contentResId,baseFragment,stack).commit();
       }else{
           fg.beginTransaction().replace(contentResId,baseFragment,stack).addToBackStack(stack).commit();
       }
    }
}
