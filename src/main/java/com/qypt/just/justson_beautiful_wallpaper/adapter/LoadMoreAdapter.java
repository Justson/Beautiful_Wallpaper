package com.qypt.just.justson_beautiful_wallpaper.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.qypt.just.justson_beautiful_wallpaper.Bean.ImageBean;
import com.qypt.just.justson_beautiful_wallpaper.Fragment.BaseFragment;
import com.qypt.just.justson_beautiful_wallpaper.LoaderManager.ImageLoader;
import com.qypt.just.justson_beautiful_wallpaper.R;

import java.util.List;

/**
 * Created by Administrator on 2016/5/31.    主页的Adapter ， 支持上拉加载更多
 */
public class LoadMoreAdapter extends RecyclerView.Adapter<LoadMoreAdapter.ViewHolder> {

    private Context context;
    private List<ImageBean>list;

    public List<ImageBean> getList() {
        return list;
    }

    public void setList(List<ImageBean> list) {
        this.list = list;
    }

    private static final int LOADER_LAYOUT=1;
    private static final int NOMAL=2;
    private LayoutInflater mLayoutInflater;
    private final ImageLoader imageLoader;
    private BaseFragment baseFragment;
    public LoadMoreAdapter(Context context, List<ImageBean> list, BaseFragment baseFragment){
        this.context=context;
        this.list=list;
        mLayoutInflater=LayoutInflater.from(context);
        imageLoader = ImageLoader.getInstance();
        this.baseFragment=baseFragment;
    }
    @Override
    public int getItemViewType(int position) {
        if(position+1==getItemCount()){
            return  LOADER_LAYOUT;
        }else{
            return NOMAL;
        }

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                if(viewType==LOADER_LAYOUT){

                    return new ViewHolder(mLayoutInflater.inflate(R.layout.recyclerview_loader_more,parent,false));
                }else{

                    return new ViewHolder(mLayoutInflater.inflate(R.layout.recyclerview_item,parent,false));
                }

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        if(position+1==getItemCount()){
            return;
        }else{

            imageLoader.asynSetImageByFragment(holder.imageView,baseFragment,list.get(position).getUrl().trim()); //设置图片
        }


    }

    @Override
    public int getItemCount() {
        return list.size()+1;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        protected ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);

            if(itemView instanceof LinearLayout)
                return;
            imageView= (ImageView) itemView.findViewById(R.id.RecylcerView_image);
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
