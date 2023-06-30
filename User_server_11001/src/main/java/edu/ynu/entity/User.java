package edu.ynu.entity;

import java.io.Serializable;

//序列化的要求
public class User implements Serializable {
    private Integer UserId;
    private String userName;
    private String password;


    public User() {
    }

    public User(Integer userId, String userName, String password) {
        UserId = userId;
        this.userName = userName;
        this.password = password;
    }


    public Integer getUserId() {
        return UserId;
    }

    public void setUserId(Integer userId) {
        UserId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
