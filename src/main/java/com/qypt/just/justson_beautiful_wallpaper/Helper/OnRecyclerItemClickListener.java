package com.qypt.just.justson_beautiful_wallpaper.Helper;

import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Administrator on 2016/5/27.
 */
public class OnRecyclerItemClickListener implements View.OnTouchListener {
   private  RecyclerView  mRecyclerView;
    private final GestureDetectorCompat gestureListener;

    public OnRecyclerItemClickListener(RecyclerView mRecyclerview){
        this.mRecyclerView=mRecyclerview;
        gestureListener = new GestureDetectorCompat(mRecyclerview.getContext(),new OnRecyclerGestureListener());
    }



    @Override
    public boolean onTouch(View v, MotionEvent event) {

      gestureListener.onTouchEvent(event);

        return false;
    }

    /**
     * 把Toutch 通过手势监听转化成监听或者长按事件
     */
    private  class OnRecyclerGestureListener extends GestureDetector.SimpleOnGestureListener{

        @Override
        public boolean onSingleTapUp(MotionEvent e) {

            View child=mRecyclerView.findChildViewUnder(e.getX(),e.getY());
            if(child!=null){
                RecyclerView.ViewHolder viewHolder=mRecyclerView.getChildViewHolder(child);
                OnRecyclerItemClickListener.this.OnItemClickListener(viewHolder);
            }

            return super.onSingleTapUp(e);
        }

        @Override
        public void onLongPress(MotionEvent e) {
            super.onLongPress(e);
            View child=mRecyclerView.findChildViewUnder(e.getX(),e.getY());
            if(child!=null)
            {
               RecyclerView.ViewHolder viewHolder= mRecyclerView.getChildViewHolder(child);
                OnRecyclerItemClickListener.this.OnItemLongClickListener(viewHolder);
            }
        }
    }

    public void OnItemClickListener(RecyclerView.ViewHolder vh){};
    public void OnItemLongClickListener(RecyclerView.ViewHolder vh){};

}
