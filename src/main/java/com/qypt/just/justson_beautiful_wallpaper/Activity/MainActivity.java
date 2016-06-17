package com.qypt.just.justson_beautiful_wallpaper.Activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.qypt.just.justson_beautiful_wallpaper.App.App;
import com.qypt.just.justson_beautiful_wallpaper.Bean.QQInfoBean;
import com.qypt.just.justson_beautiful_wallpaper.Bean.QQLoginBean;
import com.qypt.just.justson_beautiful_wallpaper.Fragment.BaseFragment;
import com.qypt.just.justson_beautiful_wallpaper.Fragment.CarFragment;
import com.qypt.just.justson_beautiful_wallpaper.Fragment.MainFragment;
import com.qypt.just.justson_beautiful_wallpaper.Fragment.SaxModelFragment;
import com.qypt.just.justson_beautiful_wallpaper.Fragment.SceneryFragment;
import com.qypt.just.justson_beautiful_wallpaper.Fragment.StarFragment;
import com.qypt.just.justson_beautiful_wallpaper.LoaderManager.ImageLoader;
import com.qypt.just.justson_beautiful_wallpaper.Presenter.MainPresenter;
import com.qypt.just.justson_beautiful_wallpaper.R;
import com.qypt.just.justson_beautiful_wallpaper.Utils.RxJavaUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Justson
 */
public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    @Bind(R.id.mToolbar)
    Toolbar mToolbar;
    @Bind(R.id.content)
    FrameLayout content;
    @Bind(R.id.nav_view)
    NavigationView navView;
    @Bind(R.id.mDrawerLayout)
    DrawerLayout mDrawerLayout;
    private ImageView login_naview;
    private static final String TAG = "MainActivity";
    private RelativeLayout unLogin_relativeLayout;
    private RelativeLayout loginRelativeLayout;
    private ImageView login_header;
    private TextView login_name;
    private MainPresenter mainPresenter = new MainPresenter();
    private Subscription compositeSubscription;
    private SharedPreferences sharedPreferences;
    private Subscription subscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(MainActivity.this);

        mToolbar.setTitle("主页");
        this.setSupportActionBar(mToolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open
                , R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        navView.setNavigationItemSelectedListener(this);

        login_naview = (ImageView) navView.findViewById(R.id.navigation_head_unlogin);
        login_naview.setOnClickListener(this);

        unLogin_relativeLayout = (RelativeLayout) navView.findViewById(R.id.main_unlogin_relativelayout);
        loginRelativeLayout = (RelativeLayout) navView.findViewById(R.id.main_login_relativelayout);
        login_header = (ImageView) navView.findViewById(R.id.navigation_head_login);
        login_name = (TextView) navView.findViewById(R.id.main_login_name);
        login_header.setOnClickListener(this);
    }

    @Override
    protected int getContentLayout() {
        return R.id.content;
    }

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null)
            return;
        getSupportFragmentManager().beginTransaction().add(getContentLayout(), new MainFragment(), "main").addToBackStack("Main").commit();

        //注册主题
        compositeSubscription = (Subscription) RxJavaUtils.getInstance().getObservable().subscribe(new Action1() {
            @Override
            public void call(Object o) {

                if (o instanceof QQLoginBean) {
                    QQLoginBean qqLoginBean = (QQLoginBean) o;
                    Log.i(TAG, "map:" + qqLoginBean);
                    onLoginFinish(qqLoginBean);
                    onStoreDataTosharepreference(qqLoginBean);
                }

            }
        });

        sharedPreferences = this.getSharedPreferences("account", MODE_PRIVATE);
        if (sharedPreferences != null) {
            initData(sharedPreferences);
        }
    }

    //初始化登陆后的数据
    private void initData(SharedPreferences sharedPreferences) {

        if (sharedPreferences.getString("Account", "-1").equals("-1"))
            return;

        unLogin_relativeLayout.setVisibility(View.GONE);
        loginRelativeLayout.setVisibility(View.VISIBLE);

        ImageLoader.getInstance().roundImageHeader(login_header, this, sharedPreferences.getString("profile_image_url", "-1"));
        login_name.setText(sharedPreferences.getString("screen_name", "***"));
    }

    //储数据到Shareperference
    private void onStoreDataTosharepreference(QQLoginBean qqLoginBean) {

        subscription = Observable.just(qqLoginBean).map(new Func1<QQLoginBean, String>() {
            @Override
            public String call(QQLoginBean qqLoginBean) {

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("Account", qqLoginBean.getContent());
                editor.putString("profile_image_url", qqLoginBean.getProfile_image_url())//
                        .putString("gender", qqLoginBean.getGender()).putString("openid", qqLoginBean.getOpenid())//
                        .putString("screen_name", qqLoginBean.getScreen_name()).putString("city", qqLoginBean.getCity())//
                        .putString("msg", qqLoginBean.getMsg());

                editor.commit();
                Log.i("Info", "CurrentThread:" + Thread.currentThread());
                return "sucess";
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.i("Info", "CurrentThreadAction:" + Thread.currentThread());
                Toast.makeText(App.getAppContext(), s, Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void onLoginFinish(QQInfoBean qqLoginBean) {

        loginRelativeLayout.setVisibility(View.VISIBLE);
        unLogin_relativeLayout.setVisibility(View.GONE);
        ImageLoader.getInstance().roundImageHeader(login_header, this, qqLoginBean.getProfile_image_url());
        login_name.setText(qqLoginBean.getScreen_name());
    }

    //获取activity的xml文件布局
    @Override
    protected int getContentViewResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        compositeSubscription.unsubscribe();
        subscription.unsubscribe();
    }


    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(navView)) {
            mDrawerLayout.closeDrawers();
            return;
        }
        if (this.getSupportFragmentManager().getBackStackEntryCount() <= 1) {
            this.finish();
        }

        super.onBackPressed();

    }

    /**
     * 转化Fragment
     *
     * @param baseFragment
     * @param tag
     */
    private void changFragment(BaseFragment baseFragment, String tag) {
        if (baseFragment == null)
            return;

        this.getSupportFragmentManager().beginTransaction().replace(getContentLayout(), baseFragment, tag)
                .commit();
        Log.i("Info", "replace");

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        int id = menuItem.getItemId();
        if (mDrawerLayout.isDrawerOpen(navView)) {
            mDrawerLayout.closeDrawers();
        }
        switch (id) {
            case R.id.start_Petty_legs:

                if (this.getSupportFragmentManager().findFragmentByTag("Pretty") != null)
                    return false;
                mToolbar.setTitle("长腿明星");
                changFragment(new StarFragment(), "Pretty");
                break;
            case R.id.main:
                if (this.getSupportFragmentManager().findFragmentByTag("Main") != null)
                    return false;
                mToolbar.setTitle("主页");
                changFragment(new MainFragment(), "Main");
                break;

            case R.id.saxModel:
                if (this.getSupportFragmentManager().findFragmentByTag("SaxModel") != null)
                    return false;
                mToolbar.setTitle("性感模特");
                changFragment(new SaxModelFragment(), "SaxModel");
                break;
            case R.id.love_car:

                if (this.getSupportFragmentManager().findFragmentByTag("Car") != null)
                    return false;
                mToolbar.setTitle("世界名车");
                changFragment(new CarFragment(), "Car");
                break;
            case R.id.preview:
                if (this.getSupportFragmentManager().findFragmentByTag("Scenery") != null)
                    return false;
                mToolbar.setTitle("风景建筑");
                changFragment(new SceneryFragment(), "Scenery");
                break;
        }

        return false;
    }

    //点击登陆头像跳转到登陆界面
    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.navigation_head_unlogin) {
            turnToActivity(AccountActivity.class, null, 0);
        }
    }
}
