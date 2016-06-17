package com.qypt.just.justson_beautiful_wallpaper.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.qypt.just.justson_beautiful_wallpaper.Activity.DetailActivity;
import com.qypt.just.justson_beautiful_wallpaper.Bean.ImageBean;
import com.qypt.just.justson_beautiful_wallpaper.LoaderManager.ImageLoader;

import java.util.List;

/**
 * Created by Administrator on 2016/5/30.  justson
 */
public class ViewPagerAdapter extends PagerAdapter implements View.OnClickListener {
    private Context context;
    private List<ImageBean>list;
    private ImageLoader imageLoader;
    public ViewPagerAdapter(Context context, List<ImageBean>list){
        this.context=context;
        this.list=list;
        imageLoader=ImageLoader.getInstance();
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        ImageView imageView=new ImageView(context);
        ViewPager.LayoutParams lp=new ViewPager.LayoutParams();
        container.addView(imageView,lp);

        imageView.setOnClickListener(this);
        imageLoader.asynSetImage(imageView,(Activity)context,list.get(position).getUrl().trim());
        return imageView;
    }

    @Override
    public void onClick(View v) {

        ((DetailActivity)context).doClick(v);
    }
}
