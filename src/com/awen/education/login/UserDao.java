package com.awen.education.login;

/**
 * @Author: awen
 * @Date: 2021/9/3 9:17
 */
public interface UserDao {
    public abstract boolean isLogin(String username, String password);
    public abstract boolean register(User user);
}

