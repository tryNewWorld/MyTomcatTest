package com.xmx.qqzone.entity;

import java.util.Date;

public class Reply {
    private Integer id;

    private String context;

    private Integer logId;

    private Date replyTime;

    private Integer replyUser;

    private UserBasic userBasic;

    private HostReply HostReply;

    public Reply() {

    }

    public Reply(String context, Integer logId, Date replyTime, Integer replyUser) {
        this.context = context;
        this.logId = logId;
        this.replyTime = replyTime;
        this.replyUser = replyUser;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public Date getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(Date replyTime) {
        this.replyTime = replyTime;
    }

    public Integer getReplyUser() {
        return replyUser;
    }

    public void setReplyUser(Integer replyUser) {
        this.replyUser = replyUser;
    }

    public com.xmx.qqzone.entity.HostReply getHostReply() {
        return HostReply;
    }

    public void setHostReply(com.xmx.qqzone.entity.HostReply hostReply) {
        HostReply = hostReply;
    }

    public UserBasic getUserBasic() {
        return userBasic;
    }

    public void setUserBasic(UserBasic userBasic) {
        this.userBasic = userBasic;
    }
}
