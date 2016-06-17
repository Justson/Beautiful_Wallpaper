package com.qypt.just.justson_beautiful_wallpaper.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.qypt.just.justson_beautiful_wallpaper.Fragment.BaseFragment;
import com.qypt.just.justson_beautiful_wallpaper.Fragment.LoginFragment;
import com.qypt.just.justson_beautiful_wallpaper.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/6/13.
 */
public class AccountActivity extends BaseActivity {
    @Bind(R.id.account_title)
    TextView accountTitle;
    @Bind(R.id.account_toolbar)
    Toolbar accountToolbar;
    @Bind(R.id.account_content)
    FrameLayout accountContent;
	private int a  =  100 000 ;

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {

        this.getSupportFragmentManager().beginTransaction().add(R.id.account_content,new LoginFragment(),"Login").commit();
    }

    @Override
    protected void initView() {
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
       this.setSupportActionBar(accountToolbar);

    }

    @Override
    protected int getContentLayout() {
        return R.id.account_content;
    }

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_account;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.i("Info","requestCode:"+requestCode+" resultCode:"+resultCode);
        BaseFragment baseFragment= (BaseFragment) this.getSupportFragmentManager().findFragmentByTag("Login");
        if(baseFragment!=null&&requestCode==11101){
            baseFragment.onActivityResult(requestCode,resultCode,data);
        }
    }
}
