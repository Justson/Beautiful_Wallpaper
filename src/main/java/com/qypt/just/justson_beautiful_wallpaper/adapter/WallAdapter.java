package com.qypt.just.justson_beautiful_wallpaper.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.qypt.just.justson_beautiful_wallpaper.Bean.ImageBean;
import com.qypt.just.justson_beautiful_wallpaper.Bean.TopBean;
import com.qypt.just.justson_beautiful_wallpaper.Fragment.BaseFragment;
import com.qypt.just.justson_beautiful_wallpaper.LoaderManager.ImageLoader;
import com.qypt.just.justson_beautiful_wallpaper.R;

import java.util.List;

/**
 * Created by Administrator on 2016/5/26.
 */
public class WallAdapter extends RecyclerView.Adapter<WallAdapter.WallHolder> {

    private Context context;
    private TopBean mTopBean;
    private List<ImageBean> list;
    private final LayoutInflater mLayoutInflater;
    private ImageLoader mImageLoader;
    private BaseFragment baseFragment;
    public WallAdapter(Context context, List<ImageBean> list, BaseFragment baseFragment) {
        this.context = context;
        this.list=list;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mImageLoader=ImageLoader.getInstance();
        this.baseFragment=baseFragment;
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public WallHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new WallHolder(mLayoutInflater.inflate(R.layout.recyclerview_item,null,false));
    }

    @Override
    public void onBindViewHolder(WallHolder holder, int position) {
        ImageBean mImageBean=(ImageBean)(list.get(position));
       mImageLoader.asynSetImageByFragment(holder.imageView,baseFragment , mImageBean.getUrl().trim());
    }

    /**
     * ViewHolder 减少findViewById
     */
    public static final class WallHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        public WallHolder(View itemView) {
            super(itemView);
            if(itemView==null)
                return;
            imageView=(ImageView)itemView.findViewById(R.id.RecylcerView_image);
            WindowManager mWindowManager= (WindowManager) itemView.getContext().getSystemService(Context.WINDOW_SERVICE);
            int width=mWindowManager.getDefaultDisplay().getWidth()/3;

            ViewGroup.LayoutParams lp=imageView.getLayoutParams();
            if(lp==null)
                return;
            lp.width=width;
            lp.height= (int) (width*1.5);
            itemView.setLayoutParams(lp);

        }
    }
}
