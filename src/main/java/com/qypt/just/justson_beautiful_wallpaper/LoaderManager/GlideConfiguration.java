package com.qypt.just.justson_beautiful_wallpaper.LoaderManager;

import android.content.Context;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.cache.MemoryCache;
import com.bumptech.glide.module.GlideModule;

/**
 * Created by Administrator on 2016/5/26.
 */
public class GlideConfiguration implements GlideModule {
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        Log.i("Info","Memory:"+Runtime.getRuntime().maxMemory()/1024+"KB");
        builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888).setMemoryCache(new MemoryCache() {
            @Override
            public int getCurrentSize() {
                return 0;
            }

            @Override
            public int getMaxSize() {
                Log.i("Info","Max size:");
                return (int) (Runtime.getRuntime().maxMemory()/2);
            }

            @Override
            public void setSizeMultiplier(float multiplier) {

            }

            @Override
            public Resource<?> remove(Key key) {
                return null;
            }

            @Override
            public Resource<?> put(Key key, Resource<?> resource) {
                return null;
            }

            @Override
            public void setResourceRemovedListener(ResourceRemovedListener listener) {

            }

            @Override
            public void clearMemory() {

            }

            @Override
            public void trimMemory(int level) {

            }
        });
    }
    @Override
    public void registerComponents(Context context,final  Glide glide) {

    }
}
