package com.qypt.just.justson_beautiful_wallpaper.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.qypt.just.justson_beautiful_wallpaper.App.App;
import com.qypt.just.justson_beautiful_wallpaper.Bean.ImageBean;
import com.qypt.just.justson_beautiful_wallpaper.R;
import com.qypt.just.justson_beautiful_wallpaper.adapter.ViewPagerAdapter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/5/27.
 */
public class DetailActivity extends AppCompatActivity {



    @Bind(R.id.framelayout_detail)
    FrameLayout framelayoutDetail;
    private static int currentPosition;
    @Bind(R.id.viewPager)
    ViewPager viewPager;

    boolean isShow = true;
    @Bind(R.id.setting_wall_detail)
    LinearLayout settingWallDetail;
    @Bind(R.id.down_detail)

    LinearLayout downDetail;
    @Bind(R.id.share_detail)
    LinearLayout shareDetail;
    @Bind(R.id.comeback_detail)
    LinearLayout comebackDetail;
    private Animation animtionHide;
    private Animation animtionShow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        App app = (App) this.getApplication();
        List<ImageBean> list = (List<ImageBean>) app.map.get("data");
        currentPosition = this.getIntent().getIntExtra("position", 0);

        //初始化动画
        animtionHide = AnimationUtils.loadAnimation(this, R.anim.hide);
        animtionShow = AnimationUtils.loadAnimation(this, R.anim.show);
        viewPager.setAdapter(new ViewPagerAdapter(this, list));
        viewPager.setCurrentItem(currentPosition);

    }


    public void startHideAnimation(final View view) {

        view.startAnimation(animtionHide);
        animtionHide.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                view.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public void startAnimationShow(final View view) {
        view.startAnimation(animtionShow);
        animtionShow.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }


    public void doClick(View v) {
        Log.i("Info", "do click");


        Log.i("Info", "isShow:" + isShow);
        if (isShow) {
            isShow = !isShow;
            startHideAnimation(comebackDetail);
        } else {
            isShow = !isShow;
            startAnimationShow(comebackDetail);
        }


    }

    @OnClick({R.id.setting_wall_detail, R.id.down_detail, R.id.share_detail, R.id.comeback_detail})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.setting_wall_detail:
                Toast.makeText(App.getAppContext(),"设置为壁纸",Toast.LENGTH_SHORT).show();
                break;
            case R.id.down_detail:
                Toast.makeText(App.getAppContext(),"下载",Toast.LENGTH_SHORT).show();
                break;
            case R.id.share_detail:
                Toast.makeText(App.getAppContext(),"分享",Toast.LENGTH_SHORT).show();
                break;
            case R.id.comeback_detail:
                Toast.makeText(App.getAppContext(),"返回",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
