package com.qypt.just.justson_beautiful_wallpaper.LoaderManager;

import android.app.Activity;
import android.view.ContextThemeWrapper;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.qypt.just.justson_beautiful_wallpaper.Fragment.BaseFragment;
import com.qypt.just.justson_beautiful_wallpaper.R;

/**
 * Created by Administrator on 2016/5/26.
 */
public class ImageLoader {

    public static ImageLoader mImageLoader;

    private ImageLoader() {

    }


    public void asynSetImage(ImageView imageView, ContextThemeWrapper contextThemeWrapper, String url) {
        if (imageView == null || contextThemeWrapper == null || url == null || url.equals(""))
            return;
        Glide.with(contextThemeWrapper).load(url).centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.loading).error(R.mipmap.login_icon).fitCenter().into(imageView);
    }


    public void asynSetImageByFragment(final ImageView imageView, final BaseFragment mFragment, String url) {
        if (imageView == null || mFragment == null || url == null || url.equals(""))
            return;
        Glide.with(mFragment).load(url).placeholder(R.drawable.loading).diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate().centerCrop().
                into(imageView);



    }

    private float getRate(int imagewidth, int width) {

        float rate=1f;

        if(imagewidth>width){
            rate=(float)((float)(imagewidth)/(float)(width));
        }
        return rate;
    }

    public void roundImageHeader(ImageView imageview ,Activity activity,String url){

        Glide.with(activity).load(url).centerCrop().dontAnimate().transform(new PhotoRoundTransform(activity,30)).into(imageview);

    }


    //单例模式
    public static ImageLoader getInstance() {

        if (mImageLoader == null) {
            synchronized (ImageLoader.class) {
                if (mImageLoader == null) {
                    mImageLoader = new ImageLoader();
                }
            }
        }

        return mImageLoader;
    }
}
