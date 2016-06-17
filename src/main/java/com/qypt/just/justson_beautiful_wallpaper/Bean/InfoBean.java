package com.qypt.just.justson_beautiful_wallpaper.Bean;

/**
 * Created by Administrator on 2016/6/1.
 */
public class InfoBean implements TopBean {

    private int pager;
    private String type;

    public int getPager() {
        return pager;
    }

    public InfoBean setPager(int pager) {
        this.pager = pager;
        return this;

    }

    public String getType() {
        return type;
    }

    public InfoBean setType(String type) {
        this.type = type;
        return this;
    }
}
