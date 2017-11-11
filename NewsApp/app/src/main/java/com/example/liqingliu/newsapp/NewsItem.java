package com.example.liqingliu.newsapp;

/**
 * Created by liqingliu on 17/11/10.
 */

public class NewsItem {

    public NewsItem(int id, String title, String desc, String picUrl, String time, String contentUrl) {
        this.id = id;
        this.title = title;
        this.desc = desc;
        this.picUrl = picUrl;
        this.time = time;
        this.contentUrl = contentUrl;
    }

    private int id;
    private String title;
    private String desc;
    private String picUrl;
    private String time;
    private String contentUrl;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContentUrl() {
        return contentUrl;
    }

    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
    }
}
