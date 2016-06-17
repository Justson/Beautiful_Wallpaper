package com.qypt.just.justson_beautiful_wallpaper.Bean;

/**
 * Created by Administrator on 2016/6/2.
 */
public class SQLiteBean  implements TopBean{

    private int id;
    private String userName;
    private String name;
    private String time;
    private String url;
    private String pagerType;
    private String type;
    private String format;
    private String width;
    private String height;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPagerType() {
        return pagerType;
    }

    public void setPagerType(String pagerType) {
        this.pagerType = pagerType;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
