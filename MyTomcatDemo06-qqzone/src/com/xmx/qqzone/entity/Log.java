package com.xmx.qqzone.entity;

import java.util.Date;
import java.util.List;

public class Log {
    private Integer id;

    private String title;

    private String context;

    private Integer author;

    private Date publishTime;

    private List<Reply> replyList;

    private UserBasic userBasic;

    public Log() {

    }

    public Log(String title, String context, Integer author, Date publishTime) {
        this.title = title;
        this.context = context;
        this.author = author;
        this.publishTime = publishTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public Integer getAuthor() {
        return author;
    }

    public void setAuthor(Integer author) {
        this.author = author;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public List<Reply> getReplyList() {
        return replyList;
    }

    public void setReplyList(List<Reply> replyList) {
        this.replyList = replyList;
    }

    public UserBasic getUserBasic() {
        return userBasic;
    }

    public void setUserBasic(UserBasic userBasic) {
        this.userBasic = userBasic;
    }
}
