package com.qypt.just.justson_beautiful_wallpaper.SQLiteDao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.qypt.just.justson_beautiful_wallpaper.Bean.SQLiteBean;
import com.qypt.just.justson_beautiful_wallpaper.Helper.WallPaperSQLiteOpenHelper;

import java.util.List;

/**
 * Created by Administrator on 2016/6/1.
 */
public class WallPaperDao {

    private final WallPaperSQLiteOpenHelper wallPaperSQLiteOpenHelper;
    private static WallPaperDao wallPaperDao;

    public WallPaperDao() {
        wallPaperSQLiteOpenHelper = WallPaperSQLiteOpenHelper.getInstance();
    }


    public static WallPaperDao getInstance() {
        if (wallPaperDao == null) {
            synchronized (WallPaperDao.class) {
                wallPaperDao = new WallPaperDao();
            }
        }
        return wallPaperDao;
    }

    private SQLiteDatabase openReadableDatabase(WallPaperSQLiteOpenHelper wallPaperSQLiteOpenHelper) {
        return wallPaperSQLiteOpenHelper.getReadableDatabase();
    }

    public void closeSQLiteDatabase() {
        SQLiteDatabase db = wallPaperSQLiteOpenHelper.getReadableDatabase();
        db.close();
        Log.i("Info", "isOpen:" + db.isOpen());
    }

    public Cursor onRawQuery(String sql, String... condition) {

        Cursor cursor = null;
        SQLiteDatabase db = openReadableDatabase(wallPaperSQLiteOpenHelper);
        cursor = db.rawQuery(sql, condition);
        return cursor;
    }

    public synchronized void  onInsertMultData(List<SQLiteBean> list) {

        SQLiteDatabase db = this.openReadableDatabase(wallPaperSQLiteOpenHelper);

        for (SQLiteBean sqLiteBean : list) {
//            Log.i("Info","insert  ");
            db.execSQL("insert into wall_paper values(?,?,?,?,?,?,?,?,?,?)",
                    new String[]{null,sqLiteBean.getUserName(), sqLiteBean.getName(),  sqLiteBean.getTime(),sqLiteBean.getUrl(), sqLiteBean.getPagerType(),
                            sqLiteBean.getType(), sqLiteBean.getFormat(), sqLiteBean.getWidth(), sqLiteBean.getHeight()});
        }
        wallPaperDao.closeSQLiteDatabase();
            if(list!=null){
                list.clear();
                list=null;
            }
    }

}
