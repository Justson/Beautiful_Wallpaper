package com.qypt.just.justson_beautiful_wallpaper.Fragment;

import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.qypt.just.justson_beautiful_wallpaper.Activity.DetailActivity;
import com.qypt.just.justson_beautiful_wallpaper.App.App;
import com.qypt.just.justson_beautiful_wallpaper.Bean.ImageBean;
import com.qypt.just.justson_beautiful_wallpaper.Bean.InfoBean;
import com.qypt.just.justson_beautiful_wallpaper.Bean.WrapperBean;
import com.qypt.just.justson_beautiful_wallpaper.Helper.OnRecyclerItemClickListener;
import com.qypt.just.justson_beautiful_wallpaper.Presenter.MainPresenter;
import com.qypt.just.justson_beautiful_wallpaper.R;
import com.qypt.just.justson_beautiful_wallpaper.SQLiteDao.WallPaperDao;
import com.qypt.just.justson_beautiful_wallpaper.Utils.UrlUtils;
import com.qypt.just.justson_beautiful_wallpaper.adapter.WallAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/5/26. justson
 */
public class StarFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    @Bind(R.id.mRecylcerView)
    RecyclerView mRecylcerView;
    protected View view;
    @Bind(R.id.mSwipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    private List<ImageBean> list = new ArrayList<ImageBean>();
    private MainPresenter mainPresenter = new MainPresenter();
    private int pager = 0;
    private boolean isLoading = false;
    private WallAdapter mWallAdapter;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_pretty;
    }

    @Override
    protected MainPresenter getMainPresenter() {
        return mainPresenter;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        list = initData();
        if (list.isEmpty()) { //如果数据库没有数据， 那么启动网络加载
            mainPresenter.dispatchTask(new InfoBean().setPager(pager).setType("sl"), UrlUtils.MAINPAGER);
        }
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light, android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mWallAdapter = new WallAdapter(getActivity(), list, this);
        mRecylcerView.setLayoutManager(new GridLayoutManager(this.getActivity(), 3));
        mRecylcerView.setAdapter(mWallAdapter);
        mRecylcerView.setItemAnimator(null);
        mRecylcerView.setOnTouchListener(new StarListener(mRecylcerView));
        /**
         * 上拉加载更多
         */
        mRecylcerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                int position = ((GridLayoutManager) (recyclerView.getLayoutManager())).findLastVisibleItemPosition();
                if (position + 1 == recyclerView.getAdapter().getItemCount() && !isLoading && dy > 0) {  //已经滑到最后一条而且用户往上拉

                    mainPresenter.dispatchTask(new InfoBean().setPager(++pager).setType("sl"), UrlUtils.MAINPAGER);

                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onRefresh() {

        mSwipeRefreshLayout.setRefreshing(true);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                new AsyncTaskRefresh().execute();

            }
        },1500);

    }

    /**
     * 开启异步任务去加载数据
     */
    private  class AsyncTaskRefresh extends AsyncTask<Void,Void,Void>{

        @Override
        protected void onPreExecute() {
            mSwipeRefreshLayout.setRefreshing(true);
            list.clear();
        }

        @Override
        protected Void doInBackground(Void... params) {

            Cursor cursor = WallPaperDao.getInstance().onRawQuery("select id,name,url,type,width,height,format from wall_paper where type=? or type=? order by RANDOM()", new String[]{"legs", "star"});
            Log.i("Info", "start Size:" + cursor.getCount());
            if (cursor != null) {
                ImageBean imageBean = null;
                int i = 0;
                while (cursor.moveToNext()) {
                    imageBean = new ImageBean();
                    imageBean.setId(cursor.getInt(0));
                    imageBean.setName(cursor.getString(1));
                    imageBean.setUrl(cursor.getString(2) == null ? "" : cursor.getString(2).trim());
                    imageBean.setType(cursor.getString(3));
                    imageBean.setWidth(cursor.getInt(4));
                    imageBean.setHeight(cursor.getInt(5));
                    imageBean.setFormat(cursor.getString(6));
                    list.add(imageBean);
                    Log.i("Info", "type:" + list.get(i++).getType());
                }
            }

                if (cursor != null) {
                    cursor.close();
                    WallPaperDao.getInstance().closeSQLiteDatabase();
                }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            mWallAdapter.notifyDataSetChanged();
            mSwipeRefreshLayout.setRefreshing(false);
            Toast.makeText(App.getAppContext(),"刷新成功...",Toast.LENGTH_SHORT).show();
        }
    }

    //监听器
    private class StarListener extends OnRecyclerItemClickListener {

        public StarListener(RecyclerView mRecyclerview) {
            super(mRecyclerview);
        }

        @Override
        public void OnItemClickListener(RecyclerView.ViewHolder vh) {

            Toast.makeText(App.getAppContext(), "点击了Item", Toast.LENGTH_LONG).show();
            App app = (App) getActivity().getApplication();
            app.map.put("data", list);
            Intent mIntent = new Intent(getActivity(), DetailActivity.class);
            mIntent.putExtra("position", vh.getAdapterPosition());
            startActivityForResult(mIntent, 0);
        }

        @Override
        public void OnItemLongClickListener(RecyclerView.ViewHolder vh) {
            super.OnItemLongClickListener(vh);
        }
    }

    //初始化数据
    private List<ImageBean> initData() {


        WallPaperDao wallPaperDao = WallPaperDao.getInstance();
        Cursor cursor = wallPaperDao.onRawQuery("select id,name,url,type,width,height,format from wall_paper where type=? or type=? order by RANDOM()", new String[]{"legs", "star"});
        Log.i("Info", "start Size:" + cursor.getCount());
        if (cursor != null) {
            ImageBean imageBean = null;
            int i = 0;
            while (cursor.moveToNext()) {
                imageBean = new ImageBean();
                imageBean.setId(cursor.getInt(0));
                imageBean.setName(cursor.getString(1));
                imageBean.setUrl(cursor.getString(2) == null ? "" : cursor.getString(2).trim());
                imageBean.setType(cursor.getString(3));
                imageBean.setWidth(cursor.getInt(4));
                imageBean.setHeight(cursor.getInt(5));
                imageBean.setFormat(cursor.getString(6));
                list.add(imageBean);
                Log.i("Info", "type:" + list.get(i++).getType());
            }

            if (cursor != null) {
                cursor.close();
                wallPaperDao.closeSQLiteDatabase();
            }
        }
        pager = list.size() / 25;
        return list;
    }

    @Override
    public <T> void onComeData(T t) {

        if (t == null)
            return;

        if (t instanceof WrapperBean) {

            Object object = ((WrapperBean) (t)).getObject();
            Log.i("Info", "object:" + object);
            if (object != null && object instanceof List) {
                List<ImageBean> newList = (List<ImageBean>) object;
                if (list != null) {
                    list.addAll(newList);
                    mWallAdapter.notifyDataSetChanged();
                }
                Log.i("Info", "newList:" + newList);
                dataChangeUpdateSQLite(newList);
            } else if (object instanceof String) {
                Toast.makeText(StarFragment.this.getActivity(), object.toString(), Toast.LENGTH_SHORT).show();
            }

        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
