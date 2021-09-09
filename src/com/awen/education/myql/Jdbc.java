package com.awen.education.myql;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @Author: awen
 * @Date: 2021/9/4 11:12
 */
public class Jdbc {
    public static Connection conn;//创建连接对象（将Connection 放在方法体外面扩大适用范围）
    //连接数据库的方法
    public static void connetionDatabse() throws Exception {
        //从properties文件中获取连接数据库的信息
        //加载驱动
        String driver=PropertiesUtil.drive().getProperty("driver");
        //数据库用户名
        String user=PropertiesUtil.drive().getProperty("user");
        //数据库密码
        String pwd=PropertiesUtil.drive().getProperty("pwd");
        //数据库url
        String url=PropertiesUtil.drive().getProperty("url");
        //1.加载驱动
        Class.forName(driver);
        conn= DriverManager.getConnection(url,user,pwd);
    }

}
