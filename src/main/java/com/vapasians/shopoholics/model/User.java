package com.vapasians.shopoholics.model;

import org.springframework.web.bind.annotation.ModelAttribute;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.stream.Stream;

@Entity
@Table(name="usertable")
public class User{

    @Id
    @Column(name="userid")
    private Integer userId;

    @Column(name="loginname")
    private String loginName;

    @Column(name="loginpwd")
    private String loginPwd;

    @Column(name="username")
    private String userName;

    @Column(name="useremail")
    private String userEmail;

    @Column(name="userphone")
    private String userPhone;

    @Column(name="useradd")
    private String userAdd;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getLoginName() {
        //System.out.println(loginName);
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPwd() {
        //System.out.println(loginPwd);
        return loginPwd;
    }

//    @Override
//    public String toString() {
//        return "User{" +
//                "userId=" + userId +
//                ", loginName='" + loginName + '\'' +
//                ", loginPwd='" + loginPwd + '\'' +
//                ", userName='" + userName + '\'' +
//                '}';
//    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserAdd() {
        return userAdd;
    }

    public void setUserAdd(String userAdd) {
        this.userAdd = userAdd;
    }
}