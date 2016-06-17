package com.qypt.just.justson_beautiful_wallpaper.Fragment;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.qypt.just.justson_beautiful_wallpaper.Activity.DetailActivity;
import com.qypt.just.justson_beautiful_wallpaper.App.App;
import com.qypt.just.justson_beautiful_wallpaper.Bean.ImageBean;
import com.qypt.just.justson_beautiful_wallpaper.Bean.InfoBean;
import com.qypt.just.justson_beautiful_wallpaper.Bean.SliderBean;
import com.qypt.just.justson_beautiful_wallpaper.Bean.SliderInfoBean;
import com.qypt.just.justson_beautiful_wallpaper.Bean.WrapperBean;
import com.qypt.just.justson_beautiful_wallpaper.Helper.OnRecyclerItemClickListener;
import com.qypt.just.justson_beautiful_wallpaper.Presenter.MainPresenter;
import com.qypt.just.justson_beautiful_wallpaper.R;
import com.qypt.just.justson_beautiful_wallpaper.SQLiteDao.WallPaperDao;
import com.qypt.just.justson_beautiful_wallpaper.Utils.UrlUtils;
import com.qypt.just.justson_beautiful_wallpaper.adapter.LoadMoreAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/5/30.
 */
public class MainFragment extends BaseFragment {
    @Bind(R.id.slider)
    SliderLayout slider;
    @Bind(R.id.recyclerview_main)
    RecyclerView recyclerviewMain;
    private int pager = 0;
    private boolean isLoading = false;

    private MainPresenter mainPresenter = new MainPresenter();
    private LoadMoreAdapter adapter;
    private static final int DEFUALTNUMBER=25;
    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_main;
    }

    @Override
    protected MainPresenter getMainPresenter() {
        return mainPresenter;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);

        return rootView;
    }

    private void init() {

        mainPresenter.dispatchTask(new SliderInfoBean().setType("slider").setPager(0),UrlUtils.SLIDER);
        List<ImageBean> list = new ArrayList<ImageBean>();
        ImageBean imageBean = null;
        WallPaperDao wallPaperDao=WallPaperDao.getInstance();
            Cursor cursor = wallPaperDao.onRawQuery("select id,name,url,type,width,height,format from wall_paper where pagerType=? order by RANDOM()", new String[]{"Main"});

            if (cursor.getCount() <= 0) {//表示第一次打开软件表为空  去服务器去数据

                mainPresenter.dispatchTask(new InfoBean().setPager(0).setType("Main"),UrlUtils.MAINPAGER);

            } else {
                while (cursor.moveToNext()) {

                    imageBean = new ImageBean();
                    imageBean.setId(cursor.getInt(0));
                    imageBean.setName(cursor.getString(1));
                    imageBean.setUrl(cursor.getString(2).trim());
                    imageBean.setType(cursor.getString(3));
                    imageBean.setWidth(cursor.getInt(4));
                    imageBean.setHeight(cursor.getInt(5));
                    imageBean.setFormat(cursor.getString(6));
                    list.add(imageBean);
                }

            }
            if (cursor != null)
            {
                Log.i("Info","cursor   ready  close");
                cursor.close();
                wallPaperDao.closeSQLiteDatabase();
            }


        pager=list.size()/DEFUALTNUMBER-1;
        adapter = new LoadMoreAdapter(getActivity(), list, this);
        recyclerviewMain.setAdapter(adapter);
        recyclerviewMain.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        recyclerviewMain.setOnTouchListener(new OnRecyclerItemClickListener(recyclerviewMain) {
            @Override
            public void OnItemClickListener(RecyclerView.ViewHolder vh) {
                ((App) (MainFragment.this.getActivity()).getApplication()).map.clear();
                ((App) (MainFragment.this.getActivity()).getApplication()).map.put("data", adapter.getList());
                Intent mIntent = new Intent(MainFragment.this.getActivity(), DetailActivity.class);
                mIntent.putExtra("position",vh.getAdapterPosition());
                Log.i("Info","url:"+adapter.getList().get(vh.getAdapterPosition()).getUrl());
                startActivity(mIntent);
            }
        });

        recyclerviewMain.setOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                Log.i("Info","state:"+newState);
//                if(newState== 0){
//                    Glide.with(MainFragment.this).resumeRequests();
//                }else if(newState==1){
//                    Glide.with(MainFragment.this).resumeRequests();
//                }else {
//                    Glide.with(MainFragment.this).pauseRequests();
//                }

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                RecyclerView.Adapter adapter = recyclerView.getAdapter();
                int position = ((GridLayoutManager) (recyclerView.getLayoutManager())).findLastVisibleItemPosition();
                if (position + 1 == adapter.getItemCount() && dy > 0) {//满足加载更多的条件, 这里说明滑动到最后了
                    if (isLoading)
                        return;
                    isLoading = true;
                    mainPresenter.dispatchTask(new InfoBean().setPager(++pager).setType("Main"), UrlUtils.MAINPAGER);
                }

            }
        });
    }



    private void initSliderMenu(List<SliderBean>list){

        if(list==null||list.isEmpty())
            return;
        TextSliderView textSliderView=null;
        for(SliderBean sliderBean:list){

           textSliderView= (TextSliderView) new TextSliderView(this.getActivity()).setScaleType(BaseSliderView.ScaleType.CenterCrop).image(sliderBean.getUrl().trim());
           slider.addSlider(textSliderView);
        }

    }
    @Override
    public <T> void onComeData(T s) {

       Object t= ((WrapperBean)(s)).getObject();
        if(((WrapperBean)(s)).getCurrentType()==WrapperBean.WrapperBeanType.SLIDE){

            if(t instanceof List)
            {
                initSliderMenu(((List<SliderBean>)(t)));
            }else{
                Toast.makeText(App.getAppContext(), t+"", Toast.LENGTH_SHORT).show();
            }

            return;
        }


        if (t instanceof List) {
            List<ImageBean> list = (List<ImageBean>) t;
            if (adapter.getItemCount() <= 10) {
                adapter.setList(list);
                adapter.notifyDataSetChanged();
            } else {
                List<ImageBean> adapterList = adapter.getList();
                adapterList.addAll(list);
                adapter.notifyDataSetChanged();
                isLoading = false;
            }
            dataChangeUpdateSQLite(list);
        } else {
            if (t == null)
                return;
            Toast.makeText(App.getAppContext(), t.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
