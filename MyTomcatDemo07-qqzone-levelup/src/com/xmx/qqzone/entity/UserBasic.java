package com.xmx.qqzone.entity;

public class UserBasic {
    private Integer id;

    private String account;

    private String pwd;

    private String name;

    public UserBasic() {
    }

    public UserBasic(String account, String pwd) {
        this.account = account;
        this.pwd = pwd;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
