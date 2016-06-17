package com.qypt.just.justson_beautiful_wallpaper.Helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.qypt.just.justson_beautiful_wallpaper.App.App;

/**
 * Created by Administrator on 2016/6/1.
 */
public class WallPaperSQLiteOpenHelper extends SQLiteOpenHelper {

    public static  WallPaperSQLiteOpenHelper wallPaperSQLiteOpenHelper;
    private static final String NAME="wall_message.db";

    public WallPaperSQLiteOpenHelper(Context context ) {
        super(context, NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql="create table wall_paper (id integer primary key autoincrement,userName,name,time,url,pagerType,type,format,width,height)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //单例  双重判空
    public static WallPaperSQLiteOpenHelper getInstance(){

        if(wallPaperSQLiteOpenHelper==null){

            synchronized (WallPaperSQLiteOpenHelper.class){

                if(wallPaperSQLiteOpenHelper==null){
                    wallPaperSQLiteOpenHelper=new WallPaperSQLiteOpenHelper(App.getAppContext());
                }
            }

        }
        return wallPaperSQLiteOpenHelper;
    }

}
