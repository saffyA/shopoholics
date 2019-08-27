package com.vapasians.shopoholics.model;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.annotation.Generated;
import javax.persistence.*;
import java.util.stream.Stream;

@Entity
@Table(name="usertable")
public class User{

    @Id
    @Column(name="userid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Column(name="role")
    private char role;

    public char getRole() {
        return role;
    }

    public void setRole(char role) {
        this.role = role;
    }

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