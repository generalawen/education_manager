package com.awen.education.login;

import java.util.Date;

/**
 * @Author: awen
 * @Date: 2021/9/2 9:23
 */
public class User {
    private String name;
    private String password;
    private Date LoginData;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLoginData() {
        return LoginData;
    }

    public void setLoginData(Date loginData) {
        LoginData = loginData;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", LoginData=" + LoginData +
                '}';
    }
}
